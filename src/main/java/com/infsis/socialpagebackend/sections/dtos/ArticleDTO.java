package com.infsis.socialpagebackend.sections.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleDTO {

    private String uuid;

    @NotBlank
    @Size(min = 36, max = 40)
    private String section_id;

   @Size(min = 36, max = 40)
    private String user_id;

    @NotNull
    private String title;

    @NotNull
    private String text;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date date;

    private List<ArticleMediaDTO> medias;
}
