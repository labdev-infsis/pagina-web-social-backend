package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setPhotoProfilePath(user.getPhotoProfilePath());
        userDTO.setPhotoPortadaPath(user.getPhotoPortadaPath());
        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPhotoProfilePath(userDTO.getPhotoProfilePath());
        user.setPhotoPortadaPath(userDTO.getPhotoPortadaPath());
        return user;
    }
}