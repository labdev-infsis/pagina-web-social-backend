package com.infsis.socialpagebackend.reactions.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class ReplyReactionDTO {

    private String uuid;

    @Size(min = 36, max = 40)
    private String user_id;

    @Size(min = 36, max = 40)
    private String reply_id;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date reaction_date;

    @NotBlank
    @Size(min = 3, max = 36)
    private String emoji_type_id;
}