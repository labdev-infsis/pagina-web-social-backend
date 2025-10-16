package com.infsis.socialpagebackend.comments.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String uuid;
    private String content;
    private boolean moderated;
    private String userId;
    private String state;
    private String user_name;
    private String user_photo;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date date;
    private int replyCount;
}