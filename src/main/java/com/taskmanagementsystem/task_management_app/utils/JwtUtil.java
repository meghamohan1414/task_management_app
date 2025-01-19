package com.taskmanagementsystem.task_management_app.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode("413F4428472B4B6250655368566D5970337336763979244226452948404D6351");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().claims(extraClaims).subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000 * 60 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith((SecretKey) getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
       final Claims claims = extractAllClaims(token);
       return claimsResolver.apply(claims);
    }

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


}
