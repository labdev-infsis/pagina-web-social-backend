package com.infsis.socialpagebackend.reactions.services;

import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.authentication.repositories.UserRepository;
import com.infsis.socialpagebackend.reactions.dtos.ReplyReactionDTO;
import com.infsis.socialpagebackend.reactions.mappers.ReplyReactionMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.reactions.models.EmojiType;
import com.infsis.socialpagebackend.reactions.models.ReplyReaction;
import com.infsis.socialpagebackend.reactions.repositories.EmojiTypeRepository;
import com.infsis.socialpagebackend.reactions.repositories.ReplyReactionRepository;
import com.infsis.socialpagebackend.replies.model.Reply;
import com.infsis.socialpagebackend.replies.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReplyReactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmojiTypeRepository emojiTypeRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ReplyReactionRepository replyReactionRepository;

    @Autowired
    private ReplyReactionMapper replyReactionMapper;

    public ReplyReactionDTO getReplyReaction(String replyUuid, String reactionUuid) {
        ReplyReaction replyReaction = replyReactionRepository.findByUuid(reactionUuid)
        .orElseThrow(() -> new NotFoundException("ReplyReaction", reactionUuid));
    
        if(replyReaction == null) {
            throw new NotFoundException("ReplyReaction", reactionUuid);
        }
        return replyReactionMapper.toDTO(replyReaction);
    }

    public List<ReplyReactionDTO> getAllReplyReactions(String replyUuid) {
        return replyReactionRepository
                .findByReplyId(replyUuid)
                .stream()
                .map(replyReaction -> replyReactionMapper.toDTO(replyReaction))
                .collect(Collectors.toList());
    }

    public ReplyReactionDTO saveReplyReaction(String replyUuid, ReplyReactionDTO replyReactionDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
    
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found: ", email));
    
        Reply reply = replyRepository.findByUuid(replyUuid);
        if (reply == null) {
            throw new NotFoundException("Reply not found: ", replyUuid);
        }
    
        EmojiType emojiType = emojiTypeRepository.findOneByUuid(replyReactionDTO.getEmoji_type_id());
        if (emojiType == null) {
            throw new NotFoundException("EmojiType not found: ", replyReactionDTO.getEmoji_type_id());
        }
    
        ReplyReaction replyReaction = replyReactionMapper.getReaction(replyReactionDTO, user, reply, emojiType);
        replyReactionRepository.save(replyReaction);
    
        return replyReactionMapper.toDTO(replyReaction);
    }

    public ReplyReactionDTO updateReplyReaction(String reactionUuid, ReplyReactionDTO updatedDto) {
    
        ReplyReaction existing = replyReactionRepository.findByUuid(reactionUuid)
            .orElseThrow(() -> new RuntimeException("Reacción no encontrada"));
    
            EmojiType emojiType = emojiTypeRepository.findOneByUuid(updatedDto.getEmoji_type_id());
            if (emojiType == null) {
                throw new RuntimeException("Emoji no válido");
            }
            
    
        existing.setEmojiType(emojiType);
        existing.setReactionDate(updatedDto.getReaction_date());

        ReplyReaction saved = replyReactionRepository.save(existing);
        return replyReactionMapper.toDTO(saved);
    }
    
    
}