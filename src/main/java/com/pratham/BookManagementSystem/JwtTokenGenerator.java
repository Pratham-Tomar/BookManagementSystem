package com.pratham.BookManagementSystem;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;

public class JwtTokenGenerator {
    public static void main(String[] args) {
        // Your Base64-encoded secret key
        String base64SecretKey = "w7aQTPH1ysHpWAvnbLQzLYNHIyaE1SnNZzSMEzC7YjM=";

        // Decode the Base64-encoded secret key
        byte[] secretKeyBytes = Base64.getDecoder().decode(base64SecretKey);

        // Generate the JWT token
        String jwtToken = Jwts.builder()
                .setSubject("admin") // Subject (e.g., username)
                .claim("role", "ADMIN") // Add claims (e.g., role)
                .setIssuedAt(new Date()) // Set issued date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expiration (1 hour)
                .signWith(SignatureAlgorithm.HS256, secretKeyBytes) // Sign with the decoded secret key
                .compact();

        // Print the generated token
        System.out.println("Bearer " + jwtToken);
    }
}