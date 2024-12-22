package com.infsis.socialpagebackend.security;

import com.infsis.socialpagebackend.repositories.InvalidTokenRepository;
import com.infsis.socialpagebackend.services.CustomUsersDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/*La función de esta clase será validar la información del token y si esto es exitoso,
establecerá la autenticación de un usuario en la solicitud o en el contexto de seguridad de nuestra aplicación*/
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUsersDetailsService customUsersDetailsService;
    @Autowired
    private JwtGenerator jwtGenerador;

    @Autowired
    private InvalidTokenRepository invalidTokenRepository;

    /*Con el siguiente método extraeremos  el token JWT de la cabecera de nuestra petición Http("Authorization")
     * luego lo validaremos y finalmente se retornará*/
    private String obtenerTokenDeSolicitud(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            //Aca si se encuentra el token JWT, se devuelve una subcadena de "bearerToken" que comienza después de los primeros 7 caracteres hasta el final de la cadena
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = obtenerTokenDeSolicitud(request);
        if (StringUtils.hasText(token) && jwtGenerador.validarToken(token)) {
            if (invalidTokenRepository.findByToken(token).isPresent()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            }
            String username = jwtGenerador.obtenerUsernameDeJwt(token);
            UserDetails userDetails = customUsersDetailsService.loadUserByUsername(username);

            // Extraer rol del token
            Claims claims = Jwts.parser().setSigningKey(ConstantsSecurity.JWT_FIRMA).parseClaimsJws(token).getBody();
            String role = claims.get("role").toString();
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role); // Asegúrate de que el rol tenga el prefijo "ROLE_"

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, Collections.singletonList(authority));
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
