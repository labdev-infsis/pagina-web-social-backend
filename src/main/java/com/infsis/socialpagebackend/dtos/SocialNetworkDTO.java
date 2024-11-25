package com.infsis.socialpagebackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SocialNetworkDTO {

    private String uuid;

    @Size(min = 36, max = 40)
    private String institution_id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 100)
    private String link;

}
