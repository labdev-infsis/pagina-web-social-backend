package com.infsis.socialpagebackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ContentDTO {

    @NotBlank
    @Size(min = 3, max = 80)
    private String text;

    @NotNull
    private List<MediaDTO> media;

    public ContentDTO() {
    }

    public ContentDTO(String text, List<MediaDTO> media) {
        this.text = text;
        this.media = media;
    }

    public @NotBlank @Size(min = 3, max = 80) String getText() {
        return text;
    }

    public void setText(@NotBlank @Size(min = 3, max = 80) String text) {
        this.text = text;
    }

    public  @NotNull List<MediaDTO> getMedia() {
        return media;
    }

    public void setMedia(@NotNull List<MediaDTO> media) {
        this.media = media;
    }
}
