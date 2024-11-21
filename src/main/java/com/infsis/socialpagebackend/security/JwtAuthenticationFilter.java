package com.infsis.socialpagebackend.security;

import com.infsis.socialpagebackend.services.CustomUsersDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/*La función de esta clase será validar la información del token y si esto es exitoso,
establecerá la autenticación de un usuario en la solicitud o en el contexto de seguridad de nuestra aplicación*/
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUsersDetailsService customUsersDetailsService;
    @Autowired
    private JwtGenerator jwtGenerador;

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
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Obtenemos los datos del token mediante el método desarrollado
        String token = obtenerTokenDeSolicitud(request);
    
        // Agrega este log para verificar si el token está siendo recibido
        System.out.println("Token recibido en el filtro: " + token);
    
        // Validamos la información del token
        if (StringUtils.hasText(token) && jwtGenerador.validarToken(token)) {
            System.out.println("Token válido. Procediendo con la autenticación.");
    
            // Obtenemos el nombre de usuario del token
            String username = jwtGenerador.obtenerUsernameDeJwt(token);
            System.out.println("Username extraído del token: " + username);
    
            // Cargamos los detalles del usuario
            UserDetails userDetails = customUsersDetailsService.loadUserByUsername(username);
    
            // Asignamos los roles y autenticamos al usuario
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    
            System.out.println("Usuario autenticado correctamente: " + username);
        } else {
            System.out.println("Token no válido o ausente.");
        }
    
        // Continuamos con la cadena de filtros
        filterChain.doFilter(request, response);
    }
    
    
}
