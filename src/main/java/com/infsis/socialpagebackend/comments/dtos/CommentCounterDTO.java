package com.infsis.socialpagebackend.comments.dtos;

import lombok.Data;

@Data
public class CommentCounterDTO {
    private int totalComments;
    private int totalReplies;
    private int totalCommentsAndReplies;
}