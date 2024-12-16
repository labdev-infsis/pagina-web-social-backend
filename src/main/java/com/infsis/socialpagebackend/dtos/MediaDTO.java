package com.infsis.socialpagebackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MediaDTO {

    @NotNull
    private Integer number;

    @NotBlank
    @Size(min = 3, max = 10)
    private String type;

    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String path;

}
