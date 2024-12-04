package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.CommentReactionDTO;
import com.infsis.socialpagebackend.dtos.CommentReactionMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.*;
import com.infsis.socialpagebackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentReactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmojiTypeRepository emojiTypeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentReactionRepository commentReactionRepository;

    @Autowired
    private CommentReactionMapper commentReactionMapper;

    public CommentReactionDTO getCommentReaction(String commentUuid, String reactionUuid) {
        CommentReaction commentReaction = commentReactionRepository.findOneByUuid(reactionUuid);
        if (commentReaction == null) {
            throw new NotFoundException("CommentReaction", reactionUuid);
        }
        return commentReactionMapper.toDTO(commentReaction);
    }

    public List<CommentReactionDTO> getAllCommentReaction(String commentUuid) {
        return commentReactionRepository
                .findByCommentId(commentUuid)
                .stream()
                .map(commentReaction -> commentReactionMapper.toDTO(commentReaction))
                .collect(Collectors.toList());
    }

    public CommentReactionDTO saveReaction(String commentUuid, CommentReactionDTO commentReactionDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found: ", email));

        Comment comment = commentRepository.findByUuid(commentUuid);
        EmojiType emojiType = emojiTypeRepository.findOneByUuid(commentReactionDTO.getEmojiTypeId());

        CommentReaction commentReaction;
        CommentReactionDTO resultDTO = new CommentReactionDTO();
        if (user != null && comment != null && emojiType != null) {
            commentReaction = commentReactionMapper.getReaction(commentReactionDTO, user, comment, emojiType);
            commentReactionRepository.save(commentReaction);

            resultDTO = commentReactionMapper.toDTO(commentReaction);
        }

        return resultDTO;
    }

}