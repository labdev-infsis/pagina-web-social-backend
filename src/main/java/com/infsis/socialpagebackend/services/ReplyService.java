package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.ReplyDTO;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.Comment;
import com.infsis.socialpagebackend.models.Reply;
import com.infsis.socialpagebackend.models.Users;
import com.infsis.socialpagebackend.repositories.CommentRepository;
import com.infsis.socialpagebackend.repositories.ReplyRepository;
import com.infsis.socialpagebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<ReplyDTO> getRepliesByCommentUuid(String commentUuid) {
        List<Reply> replies = replyRepository.findByCommentUuid(commentUuid);
        return replies.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ReplyDTO saveReply(String comment_uuid, ReplyDTO replyRequest) {
        Comment comment = commentRepository.findByUuid(comment_uuid);
        if (comment == null) {
            throw new IllegalArgumentException("Comment not found");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con email: ", email));

        Reply reply = new Reply();
        reply.setComment(comment);
        reply.setContent(replyRequest.getContent());
        reply.setUser(user);
        reply = replyRepository.save(reply);
        return convertToDTO(reply);
    }

    public void deleteReply(String uuid) {
        Reply reply = replyRepository.findByUuid(uuid);
        if (reply == null) {
            throw new NotFoundException("Reply not found with uuid: " ,uuid);
        }
        replyRepository.delete(reply);
    }

    private ReplyDTO convertToDTO(Reply reply) {
        ReplyDTO dto = new ReplyDTO();
        dto.setUuid(reply.getUuid());
        dto.setContent(reply.getContent());
        dto.setCreatedDate(reply.getCreatedAt());
        dto.setName(reply.getUser().getName());
        dto.setLastName(reply.getUser().getLastName());
        return dto;
    }
}