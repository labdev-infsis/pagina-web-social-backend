package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.UserDTO;
import com.infsis.socialpagebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO, @RequestParam Set<String> roles) {
        return userService.registerUser(userDTO, roles);
    }
}