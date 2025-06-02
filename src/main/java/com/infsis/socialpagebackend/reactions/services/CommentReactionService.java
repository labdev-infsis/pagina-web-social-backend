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
import java.util.Map;
import java.util.Optional;
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
        Users user = getAuthenticatedUser();
        Comment comment = commentRepository.findByUuid(commentUuid);
        if (comment == null) {
            throw new NotFoundException("Comment not found: ", commentUuid);
        }

        EmojiType emojiType = emojiTypeRepository.findOneByUuid(commentReactionDTO.getEmojiTypeId());
        if (emojiType == null) {
            throw new NotFoundException("EmojiType not found: ", commentReactionDTO.getEmojiTypeId());
        }

        // Verificar si ya existe una reacción del usuario para este comentario
        Optional<CommentReaction> optionalReaction = commentReactionRepository.findByCommentUuidAndUserUuid(commentUuid, user.getUuid());

        CommentReaction existingReaction = optionalReaction.orElse(null);
        CommentReaction commentReaction;
        if (existingReaction != null) {
            // Actualizar la reacción existente
            existingReaction.setEmojiType(emojiType);
            existingReaction.setReactionDate(commentReactionDTO.getReactionDate());
            commentReaction = commentReactionRepository.save(existingReaction);
        } else {
            // Crear una nueva reacción
            commentReaction = commentReactionMapper.getReaction(commentReactionDTO, user, comment, emojiType);
            commentReactionRepository.save(commentReaction);
        }

        return commentReactionMapper.toDTO(commentReaction);
    }

    public Map<String, String> deleteReaction(String commentUuid) {
        Users user = getAuthenticatedUser();
        Optional<CommentReaction> optionalReaction = commentReactionRepository.findByCommentUuidAndUserUuid(commentUuid, user.getUuid());

        if (optionalReaction.isEmpty()) {
            return Map.of("message", "No reaction found for the user on the comment.");
        }

        commentReactionRepository.delete(optionalReaction.get());
        return Map.of("message", "Reaction successfully deleted.");
    }

    private Users getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found: ", email));
    }
}