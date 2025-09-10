package com.infsis.socialpagebackend.authentication.controllers;


import com.infsis.socialpagebackend.authentication.dtos.AuthResponseDTO;
import com.infsis.socialpagebackend.authentication.dtos.UserDetailDTO;
import com.infsis.socialpagebackend.authentication.dtos.UserLoginDTO;
import com.infsis.socialpagebackend.authentication.dtos.UserRegistryDTO;
import com.infsis.socialpagebackend.authentication.models.Token;
import com.infsis.socialpagebackend.authentication.models.Role;
import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.authentication.repositories.TokenRepository;
import com.infsis.socialpagebackend.authentication.repositories.RoleRepository;
import com.infsis.socialpagebackend.authentication.repositories.UserRepository;
import com.infsis.socialpagebackend.security.JwtGenerator;
import com.infsis.socialpagebackend.authentication.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    private TokenRepository tokenRepository;

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
    public ResponseEntity<Map<String, Object>> registrar(@Valid @RequestBody UserRegistryDTO userRegistryDTO) {
        if (!userRegistryDTO.getPassword().equals(userRegistryDTO.getRepeat_password())) {
            return new ResponseEntity<>(Collections.singletonMap("message", PASSWORD_INVALID_MATCHING_MESSAGE), HttpStatus.BAD_REQUEST);
        }

        if (usuariosRepository.existsByEmail(userRegistryDTO.getEmail())) {
            return new ResponseEntity<>(Collections.singletonMap("message", REGISTERED_USER_EMAIL_MESSAGE), HttpStatus.BAD_REQUEST);
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

        return new ResponseEntity<>(Collections.singletonMap("message", SUCCESSFUL_USER_REGISTRATION_MESSAGE), HttpStatus.OK);
    }

    //Método para poder guardar usuarios de tipo ADMIN
    @PostMapping("/auth/register-adm")
    public ResponseEntity<Map<String, Object>> registrarAdmin(@RequestBody UserRegistryDTO userRegistryDTO) {

        Map<String, Object> response = new HashMap<>();

        if (!userRegistryDTO.getPassword().equals(userRegistryDTO.getRepeat_password())) {
            response.put("status", "failed");
            response.put("message", PASSWORD_INVALID_MATCHING_MESSAGE);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (usuariosRepository.existsByEmail(userRegistryDTO.getEmail())) {
            response.put("status", "failed");
            response.put("message", REGISTERED_USER_EMAIL_MESSAGE);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Users newUser = new Users();

        newUser.setName(userRegistryDTO.getName());
        newUser.setLastName(userRegistryDTO.getLastName());
        newUser.setEmail(userRegistryDTO.getEmail());
        newUser.setPhone(userRegistryDTO.getPhone());
        newUser.setEmail(userRegistryDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRegistryDTO.getPassword()));
        Role roles = rolesRepository.findByName(ROLE_ADMIN).get();
        newUser.setRoles(Collections.singletonList(roles));
        usuariosRepository.save(newUser);

        response.put("status", "success");
        response.put("message", SUCCESSFUL_USER_REGISTRATION_MESSAGE);
        response.put("data", newUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Método para poder guardar usuarios de tipo MODERATOR
    @PostMapping("/auth/register-moderator")
    public ResponseEntity<Map<String, Object>> registrarModerator(@RequestBody UserRegistryDTO userRegistryDTO) {
        Map<String, Object> response = new HashMap<>();
        if (!userRegistryDTO.getPassword().equals(userRegistryDTO.getRepeat_password())) {
            response.put("status", "failed");
            response.put("message", PASSWORD_INVALID_MATCHING_MESSAGE);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (usuariosRepository.existsByEmail(userRegistryDTO.getEmail())) {
            response.put("status", "failed");
            response.put("message", REGISTERED_USER_EMAIL_MESSAGE);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        Users newUser = new Users();

        newUser.setName(userRegistryDTO.getName());
        newUser.setLastName(userRegistryDTO.getLastName());
        newUser.setEmail(userRegistryDTO.getEmail());
        newUser.setPhone(userRegistryDTO.getPhone());
        newUser.setEmail(userRegistryDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRegistryDTO.getPassword()));
        Role roles = rolesRepository.findByName(ROLE_MODERATOR).get();
        newUser.setRoles(Collections.singletonList(roles));
        usuariosRepository.save(newUser);

        response.put("status", "success");
        response.put("message", SUCCESSFUL_USER_REGISTRATION_MESSAGE);
        response.put("data", newUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Método para poder logear un usuario y obtener un token
    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        AuthResponseDTO response = authenticationService.login(userLoginDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Token invalidToken = new Token();
        invalidToken.setToken(token);
        invalidToken.setIsRevoked(true);
        tokenRepository.save(invalidToken);
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(CLOSED_USER_SESSION_MESSAGE, HttpStatus.OK);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<AuthResponseDTO> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String refreshToken) {
        AuthResponseDTO response = authenticationService.refreshToken(refreshToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
