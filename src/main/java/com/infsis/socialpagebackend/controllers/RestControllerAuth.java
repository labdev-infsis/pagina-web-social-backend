package com.infsis.socialpagebackend.controllers;


import com.infsis.socialpagebackend.dtos.DtoRegistro;
import com.infsis.socialpagebackend.dtos.DtoLogin;
import com.infsis.socialpagebackend.dtos.DtoAuthRespuesta;
import com.infsis.socialpagebackend.models.Role;
import com.infsis.socialpagebackend.models.Users;
import com.infsis.socialpagebackend.repositories.RoleRepository;
import com.infsis.socialpagebackend.repositories.UserRepository;
import com.infsis.socialpagebackend.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.Collections;
@Validated
@RestController
@RequestMapping("/api/auth/")
public class RestControllerAuth {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private RoleRepository rolesRepository;
    private UserRepository usuariosRepository;
    private JwtGenerator jwtGenerador;

    @Autowired

    public RestControllerAuth(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RoleRepository rolesRepository, UserRepository usuariosRepository, JwtGenerator jwtGenerador) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.usuariosRepository = usuariosRepository;
        this.jwtGenerador = jwtGenerador;
    }
    //Método para poder registrar usuarios con role "user"
    @PostMapping("register")
    public ResponseEntity<String> registrar(@Valid @RequestBody DtoRegistro dtoRegistro) {

        if (!dtoRegistro.getPassword().equals(dtoRegistro.getRepeat_password())) {
            return new ResponseEntity<>("Las contraseñas no coinciden", HttpStatus.BAD_REQUEST);
        }

        if (usuariosRepository.existsByEmail(dtoRegistro.getEmail())) {
            return new ResponseEntity<>("El usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        Users usuarios = new Users();

        usuarios.setName(dtoRegistro.getName());
        usuarios.setLastName(dtoRegistro.getLastName());
        usuarios.setEmail(dtoRegistro.getEmail());
        usuarios.setPhone(dtoRegistro.getPhone());

        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Role roles = rolesRepository.findByName("STUDENT").get();
        usuarios.setRoles(Collections.singletonList(roles));
        usuariosRepository.save(usuarios);
        return new ResponseEntity<>("Registro de usuario exitoso", HttpStatus.OK);
    }

    //Método para poder guardar usuarios de tipo ADMIN
    @PostMapping("registerAdm")
    public ResponseEntity<String> registrarAdmin(@RequestBody DtoRegistro dtoRegistro) {
        if (usuariosRepository.existsByEmail(dtoRegistro.getEmail())) {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro admin", HttpStatus.BAD_REQUEST);
        }
        Users usuarios = new Users();

        usuarios.setName(dtoRegistro.getName());
        usuarios.setLastName(dtoRegistro.getLastName());
        usuarios.setEmail(dtoRegistro.getEmail());
        usuarios.setPhone(dtoRegistro.getPhone());
        usuarios.setEmail(dtoRegistro.getEmail());
        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Role roles = rolesRepository.findByName("ADMIN").get();
        usuarios.setRoles(Collections.singletonList(roles));
        usuariosRepository.save(usuarios);
        return new ResponseEntity<>("Registro de admin exitoso admin", HttpStatus.OK);
    }

    //Método para poder logear un usuario y obtener un token
    @PostMapping("login")
    public ResponseEntity<DtoAuthRespuesta> login(@RequestBody DtoLogin dtoLogin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dtoLogin.getEmail(), dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerador.generarToken(authentication);
        return new ResponseEntity<>(new DtoAuthRespuesta(token), HttpStatus.OK);
    }


}
