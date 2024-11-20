package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.PostReactionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class PostReactionController {

    @MessageMapping("/post_reaction")
    @SendTo("/topic/reaction")
    public PostReactionDTO chatWebSocket(@Payload PostReactionDTO postReactionDTO){
        log.info(postReactionDTO.toString());
        System.out.println(postReactionDTO.toString() );
        return postReactionDTO;
    }

    /*
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public PostReactionDTO sendMessage(PostReactionDTO chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public PostReactionDTO addUser(PostReactionDTO chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    **
     */


}
