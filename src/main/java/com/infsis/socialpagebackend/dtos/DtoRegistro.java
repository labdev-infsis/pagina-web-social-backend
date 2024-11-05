package com.infsis.socialpagebackend.dtos;

import lombok.Data;

@Data
public class DtoRegistro {
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String photo_profile_path;
    private String photo_portada_path;
}

