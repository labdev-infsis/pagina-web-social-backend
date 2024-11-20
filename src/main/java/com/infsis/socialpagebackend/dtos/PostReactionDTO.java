package com.infsis.socialpagebackend.dtos;

import lombok.Data;

@Data
public class PostReactionDTO {

    private String uuid;
    private String user_id;
    private String post_id;
    private String emoji_type_id;
    /*
    private String content;
    private String sender;
    private MessageType type;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }
*/


}
