package com.infsis.socialpagebackend.replies.dto;

import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.infsis.socialpagebackend.posts.dtos.ReactionCounterDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReplyDTO {
    private String uuid;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant createdDate;
    private String content;
    private String name;
    private String lastName;
    private String user_photo;
    private String parentReplyUuid;
    private ReactionCounterDTO reactions;
    private List<ReplyDTO> replies = new ArrayList<>();
}
