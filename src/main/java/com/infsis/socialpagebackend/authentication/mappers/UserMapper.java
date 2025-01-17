package com.infsis.socialpagebackend.authentication.mappers;

import com.infsis.socialpagebackend.authentication.dtos.UserDetailDTO;
import com.infsis.socialpagebackend.authentication.models.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public UserDetailDTO toDTO(Users user) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();

        userDetailDTO.setUuid(user.getUuid());
        userDetailDTO.setName(user.getName());
        userDetailDTO.setLastName(user.getLastName());
        userDetailDTO.setEmail(user.getEmail());
        userDetailDTO.setPassword(user.getPassword());
        userDetailDTO.setPhone(user.getPhone());
        userDetailDTO.setPhoto_profile_path(user.getPhoto_profile_path());
        userDetailDTO.setPhoto_cover_path(user.getPhoto_cover_path());

        return userDetailDTO;
    }

    public Users getUser(UserDetailDTO userDetailDTO) {
        Users user = new Users();

        user.setEmail(userDetailDTO.getEmail());
        user.setName(userDetailDTO.getName());
        user.setLastName(userDetailDTO.getLastName());
        user.setPassword(userDetailDTO.getPassword());
        user.setPhone(userDetailDTO.getPhone());
        user.setPhoto_profile_path(userDetailDTO.getPhoto_profile_path());
        user.setPhoto_cover_path(userDetailDTO.getPhoto_cover_path());

        return user;
    }
}
