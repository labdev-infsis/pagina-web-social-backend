package com.infsis.socialpagebackend.sections.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SectionDTO {
    private String uuid;

    @NotBlank
    @Size(min = 36, max = 40)
    private String institution_id;

    @Size(min = 36, max = 40)
    private String user_id;

    @NotNull
    private String name;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date date;

    private List<ArticleDTO> articles;

}
