package com.caseitau.config;

import com.caseitau.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${todolist.jwt.expiration}")
    private String expiration;

    @Value("${todolist.jwt.secret}")
    private String secret;

    public String generateToken(String username, Authentication authentication, String profile) {
        User logger = (User) authentication.getPrincipal();
        Date today = new Date();
        Date dateExpiration = new Date(today.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer(username)
                .setSubject(profile)
                .setIssuedAt(today)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUser(String token){
        return Jwts.parser().parseClaimsJwt(token).getBody().getIssuer();
    }

    public Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.substring(7))
                    .getBody();
        } catch (Exception e) {
//            LOGGER.error("Could not get all claims Token from passed token");
            claims = null;
        }
        return claims;
    }
}
