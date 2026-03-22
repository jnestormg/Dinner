/*package com.diner.diner.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // Genera un Key seguro HMAC-SHA256
   //private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final String SECRET = "a123456789bcdfghijk";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

   /* public String generateToken(UserDetails user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(secretKey)
                .compact();
    }
   public String generateToken(UserDetails user){
    return Jwts.builder()
            .setSubject(user.getUsername())
            .claim("roles", user.getAuthorities()) // 🔥 CLAVE
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(key)
            .compact();
}

    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
*/

package com.diner.diner.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // 🔐 Traer clave desde application.properties
    @Value("${jwt.secret}")
    private String secret;

    // 🔑 Generar key
    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // 🔥 Generar token
    public String generateToken(UserDetails user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔍 Extraer username
    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    // 🔍 Validar token
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // 🔍 Verificar expiración
    private boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // 🔍 Obtener claims
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}