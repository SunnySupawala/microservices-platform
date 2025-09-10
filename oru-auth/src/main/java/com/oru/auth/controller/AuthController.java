package com.oru.auth.controller;

import com.oru.auth.service.JwtService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public record LoginRequest(@NotBlank String username, @NotBlank String password) {}
    public record TokenResponse(String accessToken, String tokenType) {}


    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request){
        Authentication authentication =  authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())

        );
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        String token = jwtService.generateToken(request.username(), roles);
        return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
    }

    // expose public key so resource-services can validate RS256 tokens
    @GetMapping("/keys/public")
    public ResponseEntity<String> publicKey() {
        return ResponseEntity.ok(jwtService.getPublicKey());
    }
}
