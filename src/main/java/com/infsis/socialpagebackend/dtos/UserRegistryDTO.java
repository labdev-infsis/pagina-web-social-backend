package com.infsis.socialpagebackend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistryDTO {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
    @Size(min = 8, max = 20)
    private String repeat_password;

    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre solo puede contener letras y espacios")
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El apellido solo puede contener letras y espacios")
    private String lastName;

    @Pattern(regexp = "\\d{8}", message = "El número de teléfono debe contener exactamente 8 dígitos y solo puede contener números")
    private String phone;

    @Size(max = 255)
    private String photo_profile_path;

    @Size(max = 255)
    private String photo_portada_path;
}