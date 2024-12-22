package com.infsis.socialpagebackend.controllers;


import com.infsis.socialpagebackend.dtos.*;
import com.infsis.socialpagebackend.models.InvalidToken;
import com.infsis.socialpagebackend.models.Role;
import com.infsis.socialpagebackend.models.Users;
import com.infsis.socialpagebackend.repositories.InvalidTokenRepository;
import com.infsis.socialpagebackend.repositories.RoleRepository;
import com.infsis.socialpagebackend.repositories.UserRepository;
import com.infsis.socialpagebackend.security.JwtGenerator;
import com.infsis.socialpagebackend.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.infsis.socialpagebackend.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collections;
@Validated
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private static final String PASSWORD_INVALID_MATCHING_MESSAGE = "Passwords don't match, try again";
    private static final String REGISTERED_USER_EMAIL_MESSAGE = "The user email is already registered";
    private static final String SUCCESSFUL_USER_REGISTRATION_MESSAGE = "The user has been registered successfully";
    private static final String CLOSED_USER_SESSION_MESSAGE = "User session closed successfully";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_STUDENT = "STUDENT";
    private static final String ROLE_MODERATOR = "MODERATOR";

    @Autowired
    private InvalidTokenRepository invalidTokenRepository;

    @Autowired
    private AuthenticationService authenticationService;

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private RoleRepository rolesRepository;
    private UserRepository usuariosRepository;
    private JwtGenerator jwtGenerador;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RoleRepository rolesRepository, UserRepository usuariosRepository, JwtGenerator jwtGenerador) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.usuariosRepository = usuariosRepository;
        this.jwtGenerador = jwtGenerador;
    }

    //Método para poder registrar usuarios con role "user"
    @PostMapping("/auth/register")
    public ResponseEntity<String> registrar(@Valid @RequestBody UserRegistryDTO userRegistryDTO) {

        if (!userRegistryDTO.getPassword().equals(userRegistryDTO.getRepeat_password())) {
            return new ResponseEntity<>(PASSWORD_INVALID_MATCHING_MESSAGE, HttpStatus.BAD_REQUEST);
        }

        if (usuariosRepository.existsByEmail(userRegistryDTO.getEmail())) {
            return new ResponseEntity<>(REGISTERED_USER_EMAIL_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        Users usuarios = new Users();

        usuarios.setName(userRegistryDTO.getName());
        usuarios.setLastName(userRegistryDTO.getLastName());
        usuarios.setEmail(userRegistryDTO.getEmail());
        usuarios.setPhone(userRegistryDTO.getPhone());

        usuarios.setPassword(passwordEncoder.encode(userRegistryDTO.getPassword()));
        Role roles = rolesRepository.findByName(ROLE_STUDENT).get();
        usuarios.setRoles(Collections.singletonList(roles));
        usuariosRepository.save(usuarios);
        return new ResponseEntity<>(SUCCESSFUL_USER_REGISTRATION_MESSAGE, HttpStatus.OK);
    }

    //Método para poder guardar usuarios de tipo ADMIN
    @PostMapping("/auth/register-adm")
    public ResponseEntity<String> registrarAdmin(@RequestBody UserRegistryDTO userRegistryDTO) {
        if (!userRegistryDTO.getPassword().equals(userRegistryDTO.getRepeat_password())) {
            return new ResponseEntity<>(PASSWORD_INVALID_MATCHING_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        if (usuariosRepository.existsByEmail(userRegistryDTO.getEmail())) {
            return new ResponseEntity<>(REGISTERED_USER_EMAIL_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        Users usuarios = new Users();

        usuarios.setName(userRegistryDTO.getName());
        usuarios.setLastName(userRegistryDTO.getLastName());
        usuarios.setEmail(userRegistryDTO.getEmail());
        usuarios.setPhone(userRegistryDTO.getPhone());
        usuarios.setEmail(userRegistryDTO.getEmail());
        usuarios.setPassword(passwordEncoder.encode(userRegistryDTO.getPassword()));
        Role roles = rolesRepository.findByName(ROLE_ADMIN).get();
        usuarios.setRoles(Collections.singletonList(roles));
        usuariosRepository.save(usuarios);
        return new ResponseEntity<>(SUCCESSFUL_USER_REGISTRATION_MESSAGE, HttpStatus.OK);
    }

    //Método para poder guardar usuarios de tipo MODERATOR
    @PostMapping("/auth/register-moderator")
    public ResponseEntity<String> registrarModerator(@RequestBody UserRegistryDTO userRegistryDTO) {
        if (!userRegistryDTO.getPassword().equals(userRegistryDTO.getRepeat_password())) {
            return new ResponseEntity<>(PASSWORD_INVALID_MATCHING_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        if (usuariosRepository.existsByEmail(userRegistryDTO.getEmail())) {
            return new ResponseEntity<>(REGISTERED_USER_EMAIL_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        Users usuarios = new Users();

        usuarios.setName(userRegistryDTO.getName());
        usuarios.setLastName(userRegistryDTO.getLastName());
        usuarios.setEmail(userRegistryDTO.getEmail());
        usuarios.setPhone(userRegistryDTO.getPhone());
        usuarios.setEmail(userRegistryDTO.getEmail());
        usuarios.setPassword(passwordEncoder.encode(userRegistryDTO.getPassword()));
        Role roles = rolesRepository.findByName(ROLE_MODERATOR).get();
        usuarios.setRoles(Collections.singletonList(roles));
        usuariosRepository.save(usuarios);
        return new ResponseEntity<>(SUCCESSFUL_USER_REGISTRATION_MESSAGE, HttpStatus.OK);
    }
    //Método para poder logear un usuario y obtener un token
    @PostMapping("/auth/login")
    public ResponseEntity<DtoAuthRespuesta> login(@RequestBody UserLoginDTO userLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDTO.getEmail(), userLoginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerador.generarToken(authentication);
        return new ResponseEntity<>(new DtoAuthRespuesta(token), HttpStatus.OK);
    }
    @PostMapping("/auth/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        InvalidToken invalidToken = new InvalidToken();
        invalidToken.setToken(token);
        invalidToken.setInvalidatedAt(LocalDateTime.now());
        invalidTokenRepository.save(invalidToken);
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(CLOSED_USER_SESSION_MESSAGE, HttpStatus.OK);
    }

    @GetMapping("/v1/users/me")
    public UserDetailDTO getUserFromToken() {
        return authenticationService.getUserDetails();
    }

    @PutMapping("/v1/users/me")
    public UserDetailDTO updateUserProfile(@Valid @RequestBody UserDetailDTO userDetailDTO) {
        return authenticationService.updateUserProfile(userDetailDTO);
    }
}
