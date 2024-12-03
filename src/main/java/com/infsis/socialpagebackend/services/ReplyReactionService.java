package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.ReplyReactionDTO;
import com.infsis.socialpagebackend.dtos.ReplyReactionMapper;
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
        ReplyReaction replyReaction = replyReactionRepository.findOneByUuid(reactionUuid);
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
        EmojiType emojiType = emojiTypeRepository.findOneByUuid(replyReactionDTO.getEmoji_type_id());

        ReplyReaction replyReaction;
        ReplyReactionDTO resDTO = new ReplyReactionDTO();
        if(user != null && reply != null && emojiType != null) {

            replyReaction = replyReactionMapper.getReaction(replyReactionDTO, user, reply, emojiType);
            replyReactionRepository.save(replyReaction);

            resDTO = replyReactionMapper.toDTO(replyReaction);

        }

        return resDTO;
    }
}