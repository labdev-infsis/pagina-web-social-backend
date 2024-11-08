package com.infsis.socialpagebackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MediaDTO {

    @NotNull
    private Integer number;

    @NotBlank
    @Size(min = 3, max = 10)
    private String type;

    @NotBlank
    @Size(min = 3, max = 50)
    private String path;

    public MediaDTO() {
    }

    public MediaDTO(Integer number, String type, String path) {
        this.number = number;
        this.type = type;
        this.path = path;
    }

    public @NotNull Integer getNumber() {
        return number;
    }

    public void setNumber(@NotNull Integer number) {
        this.number = number;
    }

    public @NotBlank @Size(min = 3, max = 10) String getType() {
        return type;
    }

    public void setType(@NotBlank @Size(min = 3, max = 10) String type) {
        this.type = type;
    }

    public @NotBlank @Size(min = 3, max = 50) String getPath() {
        return path;
    }

    public void setPath(@NotBlank @Size(min = 3, max = 50) String path) {
        this.path = path;
    }
}
