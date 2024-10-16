package com.uade.tpo.backendGrupo6MieMa.controllers.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${application.security.jwt.secretKey}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())); // Incluye roles en el payload
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token válido por 10 horas
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public void invalidateRegistrationToken(String token) {
        // Implementa la lógica para invalidar el token de registro si es necesario
    }




    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractClaim(token, Claims::getSubject);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String extractUsername(String token) {
        String username = extractClaim(token, Claims::getSubject);
        logger.info("Extracted Username: {}", username); // Agregar log
        return username;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        logger.info("Extracting claim from token: {}", token);

        try {
            final Claims claims = extractAllClaims(token);
            T claimValue = claimsResolver.apply(claims);
            logger.info("Successfully extracted claim: {}", claimValue);
            return claimValue;
        } catch (Exception e) {
            logger.error("Failed to extract claim from token: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid token");
        }
    }


    private Claims extractAllClaims(String token) {
        logger.info("Parsing token to extract claims...");

        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            logger.info("Successfully extracted claims: {}", claims);
            return claims;
        } catch (Exception e) {
            logger.error("Failed to extract claims from token: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid token");
        }
    }



    private SecretKey getSecretKey() {
        logger.info("Generating secret key for token validation...");

        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            logger.info("Secret key generated successfully.");
            return key;
        } catch (Exception e) {
            logger.error("Failed to generate secret key: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid secret key");
        }
    }

}

