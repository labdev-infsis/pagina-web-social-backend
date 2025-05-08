package com.infsis.socialpagebackend.posts.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FacebookPhotoResponseDTO {

    @NotBlank
    private String id;

}
