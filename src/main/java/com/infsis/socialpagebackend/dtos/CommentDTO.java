package com.infsis.socialpagebackend.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String uuid;
    private String content;
    private boolean moderated;
    private String state;
    private String user_name;
    private String user_photo;
    private Date date;
    private int replyCount;
}