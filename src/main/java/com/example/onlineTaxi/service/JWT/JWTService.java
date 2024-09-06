package com.example.onlineTaxi.service.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTService {


    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    @Value("${jwt.refresh-token.expiration}")
    private long refreshExpirationMs;

    public String generateJwtToken(String username){
        Map<String, Object> extraClaims = new HashMap<>();
        return buildToken(username, jwtExpirationMs, extraClaims);
    }

    public String generateRefreshToken(String username){
        return buildToken(username, refreshExpirationMs, new HashMap<>());
    }

    public String buildToken(String username, long expirationMs , Map<String , Object> claims){

        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .and()
                .signWith(getKey())
                .compact();

    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }




    public boolean validateToken(String token) {
        try {
            extractUserName(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            // Token is invalid
        }
        return false;
    }



    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaim(token);
        return claimResolver.apply(claims);

    }


    private Claims extractAllClaim(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())  // Use the secret key to verify and parse the JWT
                .build()
                .parseClaimsJws(token)  // Parse the token and validate the signature
                .getBody();  // Get the claims body
    }


    public boolean validateToken(String token, UserDetails userDetails) {

        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
