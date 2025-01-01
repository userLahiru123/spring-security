package com.auk_development.jwt.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService() {
        try {
            SecretKey k= KeyGenerator.getInstance("HmacSHA256").generateKey();
            secretKey = Keys.hmacShaKeyFor(k.getEncoded());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String getJwtToken(){
        return Jwts.builder()
                .subject("amila")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*15))
                .signWith(secretKey)
                .compact();
    }

    public String getUserName(String token){
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build().parseSignedClaims(token)
                    .getPayload().getSubject();
        }catch (Exception e){
            return "Invalid token...";
        }
    }
}
