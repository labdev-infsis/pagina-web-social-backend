package com.infsis.socialpagebackend.security;

import com.infsis.socialpagebackend.authentication.models.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.authentication.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtGenerator {

    private final UserRepository userRepository;

    private static final String INVALID_JWT_MESSAGE = "JWT has expired or is incorrect";

    @Value("${security.jwt.expiration-time}")
    private long jwtExpirationTime;

    @Value("${security.jwt.refresh-expiration-time}")
    private long jwtRefreshExpirationTime;

    @Autowired
    public JwtGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generarAccessToken(Users user) {
        return generarToken(user, jwtExpirationTime);
    }

    public String generarRefreshToken(Users user) {
        return generarToken(user, jwtRefreshExpirationTime);
    }

    public long getExpirationTime() {
        return jwtExpirationTime;
    }

    public String obtenerUsernameDeJwt(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validarToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException(INVALID_JWT_MESSAGE);
        }
    }

    public boolean isTokenValid(String token, Users user) {
        final String username = obtenerUsernameDeJwt(token);
        return username.equals(user.getEmail()) && !isTokenExpired(token);
    }

    // Métodos privados para modularizar

    private String generarToken(Users user, long expirationTime) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getUuid())
                .claim("roles", extractRoleNames(user))
                .claim("name", user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, ConstantsSecurity.JWT_FIRMA)
                .compact();
    }

    private List<String> extractRoleNames(Users user) {
        return user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(ConstantsSecurity.JWT_FIRMA)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }
}
