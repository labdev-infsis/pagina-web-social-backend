package com.infsis.socialpagebackend.dtos;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
}