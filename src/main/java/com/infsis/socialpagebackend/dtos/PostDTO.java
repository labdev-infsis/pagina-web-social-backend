package com.infsis.socialpagebackend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class PostDTO {

    private String uuid;

    @NotBlank
    @Size(min = 36, max = 40)
    private String institution_id;

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

    private ReactionCounterDTO reactions;

    private CommentCounterDTO commentCounter;

}
