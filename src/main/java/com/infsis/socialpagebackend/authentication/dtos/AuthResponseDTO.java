package com.infsis.socialpagebackend.authentication.dtos;

import lombok.Data;

//Esta clase va a ser la que nos devolverá la información con el token y el tipo que tenga este
@Data
public class AuthResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer ";

    public AuthResponseDTO(String accessToken, String refreshToken) {

        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}