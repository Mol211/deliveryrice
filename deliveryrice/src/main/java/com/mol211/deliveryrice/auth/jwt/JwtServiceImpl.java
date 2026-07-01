package com.mol211.deliveryrice.auth.jwt;

import com.mol211.deliveryrice.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secretKey;
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Generamos token
    @Override
    public String generateToken(UserDetails userDetails) {

        HashMap<String, Object> extraClaims = new HashMap<>();

        Set<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        extraClaims.put("role", roles);

        if (userDetails instanceof User user) {
            extraClaims.put("userId", user.getId());
        }
        return buildToken(extraClaims, userDetails);

    }
    private String buildToken(HashMap<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Lee quien es el usuario
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    //Decide si aceptamos el token
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    //Comprobamos si el token ha expirado
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    //
    @Override
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    @Override
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

}
