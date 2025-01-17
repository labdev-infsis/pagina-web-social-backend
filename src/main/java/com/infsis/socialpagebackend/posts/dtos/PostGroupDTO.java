package com.infsis.socialpagebackend.posts.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostGroupDTO {

    @NotBlank
    @Size(min = 36, max = 36)
    private String group_uuid;

    private String post_uuid;

    private String status;

}
