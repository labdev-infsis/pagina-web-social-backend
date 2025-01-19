package com.infsis.socialpagebackend.replies.dto;

import com.infsis.socialpagebackend.posts.dtos.ReactionCounterDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ReplyDTO {
    private String uuid;
    private Date createdDate;
    private String content;
    private String name;
    private String lastName;
    private ReactionCounterDTO reactions;
}