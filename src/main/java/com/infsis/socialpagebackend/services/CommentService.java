package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.CommentCounterDTO;
import com.infsis.socialpagebackend.dtos.CommentDTO;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.Comment;
import com.infsis.socialpagebackend.models.Post;
import com.infsis.socialpagebackend.models.Users;
import com.infsis.socialpagebackend.repositories.CommentRepository;
import com.infsis.socialpagebackend.repositories.PostRepository;
import com.infsis.socialpagebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public CommentDTO saveComment(String postUuid, CommentDTO commentDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con email: ", email));

        Post post = postRepository.findOneByUuid(postUuid);
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(commentDTO.getContent());
        comment = commentRepository.save(comment);
        return convertToDTO(comment);
    }

    public List<CommentDTO> getCommentsByPost(String postUuid) {
        Post post = postRepository.findOneByUuid(postUuid);

        return post.getComments()
                .stream()
                .map(comment -> {
                    CommentDTO commentDTO = convertToDTO(comment);
                    commentDTO.setReplyCount(getReplyCounter(comment.getUuid()));
                    return commentDTO;
                })
                .collect(Collectors.toList());
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setUuid(comment.getUuid());
        dto.setContent(comment.getContent());
        dto.setCreatedDate(comment.getCreatedDate());
        dto.setName(comment.getUser().getName());
        dto.setLastName(comment.getUser().getLastName());
        return dto;
    }

    private int getReplyCounter(String commentUuid) {
        Comment comment = commentRepository.findByUuid(commentUuid);
        if (comment == null) {
            throw new NotFoundException("Comment", commentUuid);
        }
        return comment.getReplies().size();
    }

    public void deleteComment(String postUuid, String commentUuid) {
        Comment comment = commentRepository.findByUuid(commentUuid);
        if (comment == null) {
            throw new NotFoundException("Comment no found", commentUuid);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con email: ", email));

        if (!comment.getUser().getUuid().equals(user.getUuid())) {
            throw new RuntimeException("No tienes permiso para eliminar este comentario");
        }

        commentRepository.delete(comment);
    }
}