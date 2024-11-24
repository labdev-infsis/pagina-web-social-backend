package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.PostReactionDTO;
import com.infsis.socialpagebackend.services.PostReactionService;
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
public class PostReactionWSController {

    @Autowired
    private PostReactionService postReactionService;

    @MessageMapping("/post_reaction")
    @SendTo("/topic/reaction")
    public PostReactionDTO reactionWebSocket(@Payload PostReactionDTO postReactionDTO){
        log.info(postReactionDTO.toString());
        postReactionService.saveReaction(postReactionDTO.getUuid(), postReactionDTO);
        return postReactionDTO;
    }

}
