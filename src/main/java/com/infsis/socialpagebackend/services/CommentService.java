package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.*;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.Comment;
import com.infsis.socialpagebackend.models.Post;
import com.infsis.socialpagebackend.models.Users;
import com.infsis.socialpagebackend.repositories.CommentConfigRepository;
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

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentConfigRepository commentConfigRepository;

    public CommentDTO saveComment(String postUuid, CommentDTO commentDTO) {

        Users user = getCurrentUser();

        Post post = postRepository.findOneByUuid(postUuid);

        String commentConfig = post.getComment_conf().getConfiguration_type();

        Comment comment = commentMapper.getComment(commentDTO, user, post);

        if (commentConfig.equals(PostCommentConfigState.MODERATED_COMMENTS.name())) {
            comment.setModerated(true);
            comment.setState(CommentState.PENDING_APPROVAL.name());
        }

        comment = commentRepository.save(comment);
        return commentMapper.toDTO(comment);
    }

    public List<CommentDTO> getCommentsByPost(String postUuid) {
        Post post = postRepository.findOneByUuid(postUuid);

        return post.getComments()
                .stream()
                .filter(comment ->
                        comment.getState().equals(CommentState.VISIBLE.name())
                        || comment.getState().equals(CommentState.APPROVED.name()))
                .map(comment -> {
                    CommentDTO commentDTO = commentMapper.toDTO(comment);
                    commentDTO.setReplyCount(getReplyCounter(comment.getUuid()));
                    return commentDTO;
                })
                .collect(Collectors.toList());
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

        Users user = getCurrentUser();

        if (!comment.getUser().getUuid().equals(user.getUuid())) {
            throw new RuntimeException("No tienes permiso para eliminar este comentario");
        }

        commentRepository.delete(comment);
    }

    private Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: ", email));
    }

    public List<CommentDTO> getAllPendingModeratedComments() {
        Users users = getCurrentUser();

        List<Comment> comments = commentRepository.findAll();

        return comments
                .stream()
                .filter(comment -> comment.getState().equals(CommentState.PENDING_APPROVAL.name()))
                .map(comment -> {
                    CommentDTO commentDTO = commentMapper.toDTO(comment);
                    return commentDTO;
                })
                .collect(Collectors.toList());
    }

    public CommentDTO approvePendingModeratedComment(CommentDTO commentDTO) {

        Comment currentComment = commentRepository.findByUuid(commentDTO.getUuid());

        currentComment.setState(CommentState.APPROVED.name());

        commentRepository.save(currentComment);

        return commentMapper.toDTO(currentComment);
    }
}