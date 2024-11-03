package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.UserDTO;
import com.infsis.socialpagebackend.dtos.UserMapper;
import com.infsis.socialpagebackend.models.User;
import com.infsis.socialpagebackend.models.Rol;
import com.infsis.socialpagebackend.repositories.UserRepository;
import com.infsis.socialpagebackend.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserDTO registerUser(UserDTO userDTO, Set<String> roleNames) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }

        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Set<Rol> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Rol role = rolRepository.findByNameRole(roleName);
            if (role != null) {
                roles.add(role);
            }
        }
        user.setRoles(roles);

        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }
}