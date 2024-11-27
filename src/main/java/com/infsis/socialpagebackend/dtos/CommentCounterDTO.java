package com.infsis.socialpagebackend.dtos;

import lombok.Data;

@Data
public class CommentCounterDTO {
    private int totalComments;
    private int totalReplies;
    private int totalCommentsAndReplies;
}