package com.infsis.socialpagebackend.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class PostDTO {

    private String uuid; // Identificador único

    @NotNull
    private String institution_id; // ID de la institución

    @NotNull
    private String user_id; // ID del usuario

    @NotNull
    private String comment_config_id; // ID de configuración de comentarios



    @NotNull
    private ContentDTO content; // Contenido del post
    @NotNull
    // Nuevo atributo: Título del post
    private String title;
    
    @NotNull
    private Date postDate; // Ahora se llama postDate para consistencia
    
    public PostDTO() {
    }

    public PostDTO(String uuid, String title, ContentDTO content, Date postDate,
    String institution_id, String user_id, String comment_config_id) {
this.uuid = uuid;
this.title = title;
this.content = content;
this.postDate = postDate;
this.institution_id = institution_id != null ? institution_id : "Sin institución";
this.user_id = user_id != null ? user_id : "Sin usuario";
this.comment_config_id = comment_config_id != null ? comment_config_id : "Sin configuración de comentarios";
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


    public @NotNull ContentDTO getContent() {
        return content;
    }

    public void setContent(@NotNull ContentDTO content) {
        this.content = content;
    }

    // Métodos para title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPostDate() {
        return postDate;
    }
    
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
    
}
