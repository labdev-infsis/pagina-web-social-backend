package com.infsis.socialpagebackend.reactions.services;
import org.springframework.web.server.ResponseStatusException;

import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.authentication.repositories.UserRepository;
import com.infsis.socialpagebackend.comments.models.Comment;
import com.infsis.socialpagebackend.comments.repositories.CommentRepository;
import com.infsis.socialpagebackend.reactions.dtos.CommentReactionDTO;
import com.infsis.socialpagebackend.reactions.mappers.CommentReactionMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.reactions.models.CommentReaction;
import com.infsis.socialpagebackend.reactions.models.EmojiType;
import com.infsis.socialpagebackend.reactions.repositories.CommentReactionRepository;
import com.infsis.socialpagebackend.reactions.repositories.EmojiTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public CommentReactionDTO updateReaction(String reactionUuid, CommentReactionDTO commentReactionDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
    
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found: ", email));
    
        CommentReaction existingReaction = commentReactionRepository.findOneByUuid(reactionUuid);
    
        if (existingReaction == null) {
            throw new NotFoundException("CommentReaction", reactionUuid);
        }
    
        if (!existingReaction.getUsers().getId().equals(user.getId())) { 
          throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You can only update your own reactions.");
        }
        
    
        EmojiType newEmojiType = emojiTypeRepository.findOneByUuid(commentReactionDTO.getEmojiTypeId());
    
        if (newEmojiType == null) {
            throw new NotFoundException("EmojiType", commentReactionDTO.getEmojiTypeId());
        }
    
        existingReaction.setEmojiType(newEmojiType);
        commentReactionRepository.save(existingReaction);
    
        return commentReactionMapper.toDTO(existingReaction);
    }
    

}