package com.infsis.socialpagebackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.authentication.repositories.UserRepository;

import java.util.Date;

@Component
public class JwtGenerator {
    

    @Autowired
    private UserRepository userRepository;
    private final static String INVALID_JWT_MESSAGE = "Jwt has expired or is incorrect";

    @Value("${security.jwt.expiration-time}")
    private long jwtExpirationTime;

    public long getExpirationTime() {
        return jwtExpirationTime;
    }

        // 🔥 Inyectamos el UserRepository en el constructor
    public JwtGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   // Método para crear un token con userId incluido
   public String generarToken(Authentication authentication) {
    String username = authentication.getName();
    Users user = userRepository.findByEmail(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    String role = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .findFirst()
            .orElse("");

    return Jwts.builder()
            .setSubject(username)
            .claim("userId", user.getUuid())  // ✅ Usa el UUID correctamente
            .claim("role", role)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationTime))
            .signWith(SignatureAlgorithm.HS512, ConstantsSecurity.JWT_FIRMA)
            .compact();
}



    //Método para extraer un Username apartir de un token
    public String obtenerUsernameDeJwt(String token) {
        Claims claims = Jwts.parser() //El método parser se utiliza con el fin de analizar el token
                .setSigningKey(ConstantsSecurity.JWT_FIRMA)// Establece la clave de firma, que se utiliza para verificar la firma del token
                .parseClaimsJws(token) //Se utiliza para verificar la firma del token, apartir del String "token"
                .getBody(); /*Obtenemos el claims(cuerpo) ya verificado del token el cual contendrá la información de
                nombre de usuario, fecha de expiración y firma del token*/
        return claims.getSubject(); //Devolvemos el nombre de usuario o email
    }

    //Método para validar el token
    public Boolean validarToken(String token) {
        try {
            //Validación del token por medio de la firma que contiene el String token(token)
            //Si son idénticas validara el token o caso contrario saltara la excepción de abajo
            Jwts.parser().setSigningKey(ConstantsSecurity.JWT_FIRMA).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException(INVALID_JWT_MESSAGE);
        }
    }
}
