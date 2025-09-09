package com.oru.auth.service;

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.oru.auth.util.PemUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    @Value("${app.jwt.issuer}")
    private String issuer;

    @Value("${app.jwt.access-token-minutes:60}")
    private long accessTokenMinutes;

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public JwtService(){
        try{
            //load private key

            ClassPathResource privateResource = new ClassPathResource("private.pem");
            ClassPathResource publicResource = new ClassPathResource("public.pem");

            this.privateKey = PemUtils.readPrivateKey(privateResource.getInputStream(), "RSA");
            this.publicKey = PemUtils.readPublicKey(publicResource.getInputStream(), "RSA");


            /*try(InputStream is = getClass().getResourceAsStream("/keys/private.pem")){
                if(is == null) throw new IllegalStateException("Private key not found");
                String pem = new String(is.readAllBytes(), StandardCharsets.UTF_8)
                        .replace("-----BEGIN PRIVATE KEY-----", "")
                        .replace("-----END PRIVATE KEY-----", "")
                        .replaceAll("\\s", "");
                byte[] pkcs8 =  java.util.Base64.getDecoder().decode(pem);
                KeyFactory kf = KeyFactory.getInstance("RSA");
                privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(pkcs8));
            }*/

            // Load public key (exposed for resource services)
            /*try (InputStream is = getClass().getResourceAsStream("/keys/public.pem")) {
                if (is == null) throw new IllegalStateException("Public key not found");
                publicKeyPem = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }*/
        }catch (Exception ex){
            throw new RuntimeException("Failed to load keys", ex);
        }

    }


    public String generateToken(String subject, List<String> roles) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(accessTokenMinutes * 60);

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(subject)
                .issuer(issuer)
                .issueTime(Date.from(now))
                .expirationTime(Date.from(exp))
                .claim("roles", roles)
                .build();

        try {
            JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
                    .type(JOSEObjectType.JWT)
                    .build();

            SignedJWT jwt = new SignedJWT(jwsHeader, claims);
            JWSSigner signer = new RSASSASigner(privateKey);
            jwt.sign(signer);

            return jwt.serialize();
        }catch (JOSEException jsonEOFException){
            throw new RuntimeException("Failed to sign JWT", jsonEOFException);
        }

    }
}
