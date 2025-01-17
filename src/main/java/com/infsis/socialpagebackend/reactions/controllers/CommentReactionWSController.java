package com.infsis.socialpagebackend.reactions.controllers;

import com.infsis.socialpagebackend.reactions.dtos.CommentReactionDTO;
import com.infsis.socialpagebackend.reactions.services.CommentReactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * It sends websocket messages to the /topic/reaction channel and
 * receives though the channel /app/post_reaction
 */
@Controller
@Slf4j
public class CommentReactionWSController {

    @Autowired
    private CommentReactionService commentReactionService;

    @MessageMapping("/comment_reaction")
    @SendTo("/topic/reaction")
    public CommentReactionDTO reactionWebSocket(@Payload CommentReactionDTO commentReactionDTO) {
        log.info(commentReactionDTO.toString());
        commentReactionService.saveReaction(commentReactionDTO.getUuid(), commentReactionDTO);
        return commentReactionDTO;
    }

}