package com.oru.auth.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class PemUtils {

    private static String readKey(InputStream inputStream) throws Exception {
        try (Reader reader = new InputStreamReader(inputStream)) {
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            return sb.toString()
                    .replaceAll("-----BEGIN (.*)-----", "")
                    .replaceAll("-----END (.*)-----", "")
                    .replaceAll("\\s", "");
        }
    }

    public static PrivateKey readPrivateKey(InputStream inputStream, String algorithm) throws Exception {
        String key = readKey(inputStream);
        byte[] encoded = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return KeyFactory.getInstance(algorithm).generatePrivate(keySpec);
    }

    public static PublicKey readPublicKey(InputStream inputStream, String algorithm) throws Exception {
        String key = readKey(inputStream);
        byte[] encoded = Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        return KeyFactory.getInstance(algorithm).generatePublic(keySpec);
    }
}
