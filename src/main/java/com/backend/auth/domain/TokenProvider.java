package com.backend.auth.domain;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

    private final SecretKey secretKey;
    private static final long TOKEN_VALID_TIME = 1000L * 360;

    public TokenProvider(@Value("${secret.key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(UTF_8));
    }

    public String createToken(Long userId, String role) {
        Date now = new Date();
        return Jwts.builder()
            .subject(userId.toString())
            .claim("role", role)
            .issuedAt(now)
            .expiration(new Date(now.getTime() + TOKEN_VALID_TIME))
            .signWith(secretKey)
            .compact();
    }

    public Long getUserId(String token) {
        return Long.valueOf(Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject());
    }

    public String getUserRole(String token) {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .get("role", String.class);
    }


}
