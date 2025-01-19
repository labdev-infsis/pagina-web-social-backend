package com.infsis.socialpagebackend.reactions.mappers;

import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.posts.models.Post;
import com.infsis.socialpagebackend.reactions.dtos.PostReactionDTO;
import com.infsis.socialpagebackend.reactions.models.EmojiType;
import com.infsis.socialpagebackend.reactions.models.PostReaction;
import org.springframework.stereotype.Component;

@Component
public class PostReactionMapper {

    public PostReactionDTO toDTO(PostReaction postReaction) {

        PostReactionDTO postReactionDTO = new PostReactionDTO();
        postReactionDTO.setUuid(postReaction.getUuid());
        postReactionDTO.setUser_id(postReaction.getUsers().getUuid());
        postReactionDTO.setPost_id(postReaction.getPost().getUuid());
        postReactionDTO.setReaction_date(postReaction.getReaction_date());
        postReactionDTO.setEmoji_type_id(postReaction.getEmoji_type().getUuid());

        return postReactionDTO;
    }

    public PostReaction getReaction(PostReactionDTO postReactionDTO, Users users, Post post, EmojiType emojiType) {

        PostReaction postReaction = new PostReaction();

        postReaction.setUsers(users);
        postReaction.setPost(post);
        postReaction.setEmoji_type(emojiType);
        postReaction.setReaction_date(postReactionDTO.getReaction_date());

        return postReaction;
    }
}
