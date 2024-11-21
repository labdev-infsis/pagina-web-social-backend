package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.PostDTO;
import com.infsis.socialpagebackend.dtos.PostReactionDTO;
import com.infsis.socialpagebackend.dtos.PostReactionMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.*;
import com.infsis.socialpagebackend.repositories.EmojiTypeRepository;
import com.infsis.socialpagebackend.repositories.PostRepository;
import com.infsis.socialpagebackend.repositories.ReactionRepository;
import com.infsis.socialpagebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ReactionRepository reactionRepository;

    @Autowired
    private PostReactionMapper postReactionMapper;

    public PostReactionDTO getPostReaction(String postReactionUuid) {
        Reaction reaction = reactionRepository.findOneByUuid(postReactionUuid);
        if(reaction == null) {
            throw new NotFoundException("Reaction", postReactionUuid);
        }
        return postReactionMapper.toDTO(reaction);
    }

    public List<PostReactionDTO> getAllPostReaction() {
        return reactionRepository
                .findAll()
                .stream()
                .map(postReaction -> postReactionMapper.toDTO(postReaction))
                .collect(Collectors.toList());
    }

    public PostReactionDTO saveReaction(PostReactionDTO postReactionDTO) {

        EmojiType emojiType = emojiTypeRepository.findOneByUuid(postReactionDTO.getEmoji_type_id());
        Users users = userRepository.findOneByUuid(postReactionDTO.getUser_id());
        Post post = postRepository.findOneByUuid(postReactionDTO.getPost_id());

        Reaction reaction = new Reaction();
        PostReactionDTO resDTO = new PostReactionDTO();
        if(users != null && post != null && emojiType != null) {

            reaction = postReactionMapper.getReaction(postReactionDTO, users, post, emojiType);
            reactionRepository.save(reaction);

            resDTO = postReactionMapper.toDTO(reaction);

        }

        return resDTO;
        
    }

}
