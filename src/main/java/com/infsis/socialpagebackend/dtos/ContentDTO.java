package com.infsis.socialpagebackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ContentDTO {

    @NotBlank
    @Size(min = 3, max = 80)
    private String text;

    @NotNull
    private List<MediaDTO> media;

}
