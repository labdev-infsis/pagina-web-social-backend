package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.*;
import org.springframework.stereotype.Component;

@Component
public class ReplyReactionMapper {

    public ReplyReactionDTO toDTO(ReplyReaction replyReaction) {
        ReplyReactionDTO dto = new ReplyReactionDTO();
        dto.setUuid(replyReaction.getUuid());
        dto.setUser_id(replyReaction.getUser().getUuid());
        dto.setReply_id(replyReaction.getReply().getUuid());
        dto.setReaction_date(replyReaction.getReactionDate());
        dto.setEmoji_type_id(replyReaction.getEmojiType().getUuid());
        return dto;
    }

    public ReplyReaction getReaction(ReplyReactionDTO dto, Users user, Reply reply, EmojiType emojiType) {
        ReplyReaction replyReaction = new ReplyReaction();
        replyReaction.setUser(user);
        replyReaction.setReply(reply);
        replyReaction.setEmojiType(emojiType);
        replyReaction.setReactionDate(dto.getReaction_date());
        return replyReaction;
    }
}