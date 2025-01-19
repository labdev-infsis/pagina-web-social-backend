package com.infsis.socialpagebackend.posts.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentConfigDTO {

    private String uuid;

    private String name;

    @NotBlank
    @Size(min = 3, max = 200)
    private String configuration_type;


}
