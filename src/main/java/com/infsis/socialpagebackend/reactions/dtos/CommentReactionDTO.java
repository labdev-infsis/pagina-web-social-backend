package com.infsis.socialpagebackend.reactions.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class CommentReactionDTO {

    private String uuid;

    @Size(min = 36, max = 40)
    private String userId;

    @Size(min = 36, max = 40)
    private String commentId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date reactionDate;

    @NotBlank
    @Size(min = 3, max = 36)
    private String emojiTypeId;

}
