package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.*;
import org.springframework.stereotype.Component;

@Component
public class CommentReactionMapper {

    public CommentReactionDTO toDTO(CommentReaction commentReaction) {
        CommentReactionDTO commentReactionDTO = new CommentReactionDTO();
        commentReactionDTO.setUuid(commentReaction.getUuid());
        commentReactionDTO.setUserId(commentReaction.getUsers().getUuid());
        commentReactionDTO.setCommentId(commentReaction.getComment().getUuid());
        commentReactionDTO.setReactionDate(commentReaction.getReactionDate());
        commentReactionDTO.setEmojiTypeId(commentReaction.getEmojiType().getUuid());

        return commentReactionDTO;
    }

    public CommentReaction getReaction(CommentReactionDTO commentReactionDTO, Users users, Comment comment, EmojiType emojiType) {
        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setUsers(users);
        commentReaction.setComment(comment);
        commentReaction.setEmojiType(emojiType);
        commentReaction.setReactionDate(commentReactionDTO.getReactionDate());

        return commentReaction;
    }

}
