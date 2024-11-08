package com.infsis.socialpagebackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentConfigDTO {

    private String uuid;

    @NotBlank
    @Size(min = 3, max = 200)
    private String configuration;

    public CommentConfigDTO() {
    }

    public CommentConfigDTO(String uuid, String configuration) {
        this.uuid = uuid;
        this.configuration = configuration;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public @NotBlank @Size(min = 3, max = 200) String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(@NotBlank @Size(min = 3, max = 200) String configuration) {
        this.configuration = configuration;
    }
}
