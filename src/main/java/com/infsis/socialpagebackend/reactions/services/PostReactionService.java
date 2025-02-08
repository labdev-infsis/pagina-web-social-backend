package com.infsis.socialpagebackend.reactions.services;

import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.reactions.dtos.PostReactionDTO;
import com.infsis.socialpagebackend.reactions.mappers.PostReactionMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.posts.models.Post;
import com.infsis.socialpagebackend.reactions.models.EmojiType;
import com.infsis.socialpagebackend.reactions.models.PostReaction;
import com.infsis.socialpagebackend.reactions.repositories.EmojiTypeRepository;
import com.infsis.socialpagebackend.posts.repositories.PostRepository;
import com.infsis.socialpagebackend.reactions.repositories.PostReactionRepository;
import com.infsis.socialpagebackend.authentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PostReactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmojiTypeRepository emojiTypeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostReactionRepository postReactionRepository;

    @Autowired
    private PostReactionMapper postReactionMapper;

    public PostReactionDTO getPostReaction(String postUuid, String reactionUuid) {
        PostReaction postReaction = postReactionRepository.findOneByUuid(reactionUuid);
        if(postReaction == null) {
            throw new NotFoundException("PostReaction", reactionUuid);
        }
        return postReactionMapper.toDTO(postReaction);
    }

    public List<PostReactionDTO> getAllPostReaction(String postUuid) {
        return postReactionRepository
                .findByPostId(postUuid)
                .stream()
                .map(postReaction -> postReactionMapper.toDTO(postReaction))
                .collect(Collectors.toList());
    }

    public PostReactionDTO saveReaction(String postUuid, PostReactionDTO postReactionDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found: ", email));

        Post post = postRepository.findOneByUuid(postUuid);
        EmojiType emojiType = emojiTypeRepository.findOneByUuid(postReactionDTO.getEmoji_type_id());

        //PostReaction postReactionFound = postReactionRepository.findByUserUuid(user.getUuid());

        PostReaction currentPostReaction = postReactionRepository.findByPostUuidAndUserUuid(postUuid, user.getUuid());
        PostReaction postReaction = new PostReaction();
        PostReactionDTO resDTO = new PostReactionDTO();

        if(currentPostReaction != null) {
            currentPostReaction.setEmoji_type(emojiType);
            currentPostReaction.setReaction_date(postReactionDTO.getReaction_date());
            postReactionRepository.save(currentPostReaction);
            postReaction = currentPostReaction;
        } else {
            if(user != null && post != null && emojiType != null) {
                postReaction = postReactionMapper.getReaction(postReactionDTO, user, post, emojiType);
                postReactionRepository.save(postReaction);
            }
        }
        resDTO = postReactionMapper.toDTO(postReaction);
        return resDTO;
        
    }
    public void deleteReaction(String postUuid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found: ", email));

        PostReaction postReaction = postReactionRepository.findByPostUuidAndUserUuid(postUuid, user.getUuid());
        if (postReaction == null) {
            throw new NotFoundException("Reaction not found for the user on the post: ", postUuid);
        }
        postReactionRepository.delete(postReaction);
    }
}
