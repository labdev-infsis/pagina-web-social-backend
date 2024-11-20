package com.infsis.socialpagebackend.controllers;

import lombok.extern.slf4j.Slf4j;
import com.infsis.socialpagebackend.dtos.PostReaction;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ReactionPostController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public PostReaction chatWebSocket(@Payload PostReaction postReaction){
        log.info(postReaction.toString());
        System.out.println(postReaction.toString() );
        return postReaction;
    }

    /*
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public PostReaction sendMessage(PostReaction chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public PostReaction addUser(PostReaction chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    **
     */


}
