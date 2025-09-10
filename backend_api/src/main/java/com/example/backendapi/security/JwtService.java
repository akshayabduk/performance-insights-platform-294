package com.example.backendapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

/**
 * PUBLIC_INTERFACE
 * Service for JWT creation, parsing, and validation.
 */
@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms:86400000}")
    private long jwtExpirationMs;

    private Key key() {
        // The secret should be a sufficiently long Base64-encoded string
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // PUBLIC_INTERFACE
    public String generateToken(String username, Collection<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    // PUBLIC_INTERFACE
    public String extractUsername(String token) {
        return parseClaims(token).getBody().getSubject();
    }

    // PUBLIC_INTERFACE
    public List<String> extractRoles(String token) {
        Claims claims = parseClaims(token).getBody();
        Object rawRoles = claims.get("roles");
        if (rawRoles instanceof Collection<?> c) {
            return c.stream().map(String::valueOf).collect(Collectors.toList());
        }
        return List.of();
    }

    // PUBLIC_INTERFACE
    public boolean isTokenValid(String token, String username) {
        try {
            Claims claims = parseClaims(token).getBody();
            String sub = claims.getSubject();
            Date exp = claims.getExpiration();
            return sub.equals(username) && exp.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token);
    }
}
