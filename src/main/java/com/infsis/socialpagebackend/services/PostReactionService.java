package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.PostReactionDTO;
import com.infsis.socialpagebackend.dtos.PostReactionMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.*;
import com.infsis.socialpagebackend.repositories.EmojiTypeRepository;
import com.infsis.socialpagebackend.repositories.PostRepository;
import com.infsis.socialpagebackend.repositories.PostReactionRepository;
import com.infsis.socialpagebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
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
        /*
        return postReactionRepository
                        .findAll()
                        .stream()
                        .filter(postReaction -> postReaction.getPost().getUuid().equals(postUuid))
                        .map(postReaction -> postReactionMapper.toDTO(postReaction))
                        .collect(Collectors.toList());
        */
    }

    public PostReactionDTO saveReaction(String postUuid, PostReactionDTO postReactionDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found: ", email));

        //Users user = userRepository.findOneByUuid(postReactionDTO.getUser_id());
        Post post = postRepository.findOneByUuid(postUuid);
        EmojiType emojiType = emojiTypeRepository.findOneByUuid(postReactionDTO.getEmoji_type_id());

        PostReaction postReaction;
        PostReactionDTO resDTO = new PostReactionDTO();
        if(user != null && post != null && emojiType != null) {

            postReaction = postReactionMapper.getReaction(postReactionDTO, user, post, emojiType);
            postReactionRepository.save(postReaction);

            resDTO = postReactionMapper.toDTO(postReaction);

        }

        return resDTO;
        
    }

}
