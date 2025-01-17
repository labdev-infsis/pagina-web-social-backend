package com.infsis.socialpagebackend.authentication.dtos;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
}