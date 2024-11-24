package com.infsis.socialpagebackend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Size;


import java.util.Date;

public class PostDTO {

    private String uuid; // Identificador único para la publicación

    @NotNull
    private String institution_id; // ID de la institución asociada


    @NotNull
    private String user_id; // ID del usuario que creó la publicación

    @NotNull
    private String comment_config_id; // ID de configuración de comentarios

    @NotNull
    private Date date; // Fecha de la publicación

    @NotNull
    private ContentDTO content; // Contenido asociado a la publicación

    @NotNull
    private String title; // Título de la publicación

    @NotBlank
    @Size(min = 36, max = 40)
    private String institution_id;

    @NotBlank
    @Size(min = 36, max = 40)
    private String user_id;

    @NotBlank
    @Size(min = 36, max = 40)
    private String comment_config_id;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date date;

    @NotNull
    private ContentDTO content;


    // Constructor vacío para inicializar sin parámetros
    public PostDTO() {
    }

    // Getters y Setters
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public @NotNull String getInstitution_id() {
        return institution_id;
    }

    public void setInstitution_id(@NotNull String institution_id) {
        this.institution_id = institution_id;
    }

    public @NotNull String getUser_id() {
        return user_id;
    }

    public void setUser_id(@NotNull String user_id) {
        this.user_id = user_id;
    }

    public @NotNull String getComment_config_id() {
        return comment_config_id;
    }

    public void setComment_config_id(@NotNull String comment_config_id) {
        this.comment_config_id = comment_config_id;
    }

    public @NotNull Date getDate() {
        return date;
    }

    public void setDate(@NotNull Date date) {
        this.date = date;
    }

    public @NotNull ContentDTO getContent() {
        return content;
    }

    public void setContent(@NotNull ContentDTO content) {
        this.content = content;
    }

    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

 
}
