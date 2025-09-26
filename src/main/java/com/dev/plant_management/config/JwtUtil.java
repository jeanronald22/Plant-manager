package com.dev.plant_management.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private final String SECRET_KEY = "my_secret_key"; // TODO: externaliser dans application.properties


    public String generateToken(String username) {
        logger.debug("Generating token for user: {}", username);
        long EXPIRATION_TIME = 1000 * 60 * 60; // 1h
        String token = JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
        logger.info("Token generated successfully for user: {}", username);
        return token;
    }


    public String extractUsername(String token) {
        try {
            String username = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token)
                    .getSubject();
            logger.debug("Extracted username: {}", username);
            return username;
        } catch (JWTVerificationException e) {
            logger.error("Invalid token while extracting username: {}", e.getMessage());
            throw e;
        }
    }

    public boolean validateToken(String token, String username) {
        try {
            String extractedUsername = extractUsername(token);
            boolean valid = extractedUsername.equals(username) && !isTokenExpired(token);
            logger.debug("Validation result for token (user={}): {}", username, valid);
            return valid;
        } catch (Exception e) {
            logger.error("Token validation failed: {}", e.getMessage());
            return false;
        }
    }


    private boolean isTokenExpired(String token) {
        Date expiration = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getExpiresAt();
        boolean expired = expiration.before(new Date());
        logger.debug("Token expiration check: {} (expired={})", expiration, expired);
        return expired;
    }
}
