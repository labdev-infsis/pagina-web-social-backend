package com.infsis.socialpagebackend.dtos;

import lombok.Data;

@Data
public class DtoUserDetails {
    private String name;
    private String lastName;
    private String uuid;
    private String email;
    private String phone;
    private String photoProfilePath;
    private String photoPortadaPath;
}