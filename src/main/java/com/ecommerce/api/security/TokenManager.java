package com.ecommerce.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TokenManager implements Serializable {

    private static final long serialVersionUID = 1677982526731439916L;
    private static final String SECRET_KEY = "malumProje";

    public String generateJwtToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        String authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(String::toUpperCase)
                .collect(Collectors.joining(","));
        claims.put("authorities", authorities);

        Date date = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(date.getTime() + (15 * 60 * 1000));
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(date)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public Boolean validateJwtToken(String token, UserDetails userDetails) {

        String email = getEmailFromToken(token);
        Claims claims = getClaims(token);
        boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (email.equals(userDetails.getUsername()) && !isTokenExpired);
    }

    public String getEmailFromToken(String token) {
        return getClaims(token)
                .getSubject();
    }


    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
