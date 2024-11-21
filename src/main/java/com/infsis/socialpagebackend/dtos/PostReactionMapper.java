package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.*;
import org.springframework.stereotype.Component;

@Component
public class PostReactionMapper {

    public PostReactionDTO toDTO(Reaction reaction) {

        PostReactionDTO postReactionDTO = new PostReactionDTO();
        postReactionDTO.setUuid(reaction.getUuid());
        postReactionDTO.setUser_id(reaction.getUsers().getUuid());
        postReactionDTO.setEmoji_type_id(reaction.getEmoji_type().getUuid());

        return postReactionDTO;
    }

    public Reaction getReaction(PostReactionDTO postReactionDTO, Users users, Post post, EmojiType emojiType) {

        Reaction reaction = new Reaction();

        reaction.setUuid(postReactionDTO.getUuid());
        //reaction.setUsers(postReactionDTO.getUser_id());
        //reaction.setEmoji_type(postReactionDTO.getEmoji_type_id());

        return reaction;
    }
}
