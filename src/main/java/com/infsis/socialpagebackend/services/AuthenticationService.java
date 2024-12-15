package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.UserDetailDTO;
import com.infsis.socialpagebackend.dtos.UserMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.Users;
import com.infsis.socialpagebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDetailDTO updateUserProfile(UserDetailDTO userDetailDTO) {

        Users currentUser = getCurrentUser();

        currentUser.setEmail(userDetailDTO.getEmail());
        currentUser.setName(userDetailDTO.getName() != null ? userDetailDTO.getName() : currentUser.getEmail());
        currentUser.setLastName(userDetailDTO.getLastName() != null ? userDetailDTO.getLastName() : currentUser.getLastName());
        currentUser.setPassword(userDetailDTO.getPassword() != null ? userDetailDTO.getPassword() : currentUser.getPassword());
        currentUser.setPhone(userDetailDTO.getPhone() != null ? userDetailDTO.getPhone() : currentUser.getPhone());
        currentUser.setPhoto_profile_path(userDetailDTO.getPhoto_profile_path() != null ? userDetailDTO.getPhoto_profile_path() : currentUser.getPhoto_profile_path());
        currentUser.setPhoto_cover_path(userDetailDTO.getPhoto_cover_path() != null ? userDetailDTO.getPhoto_cover_path() : currentUser.getPhoto_cover_path());

        userRepository.save(currentUser);

        return userMapper.toDTO(currentUser);
    }

    public UserDetailDTO getUserDetails() {
        Users currentUser = getCurrentUser();
        return userMapper.toDTO(currentUser);
    }

    private Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: ", email));
    }


}
