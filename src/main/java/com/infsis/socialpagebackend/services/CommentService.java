package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.CommentDTO;
import com.infsis.socialpagebackend.models.Comment;
import com.infsis.socialpagebackend.models.Post;
import com.infsis.socialpagebackend.repositories.CommentRepository;
import com.infsis.socialpagebackend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public CommentDTO saveComment(String postUuid, CommentDTO commentDTO) {
        Post post = postRepository.findOneByUuid(postUuid);
        Comment comment = new Comment();
        comment.setPost(post);
       // comment.setContent(commentDTO.getContent());
        comment = commentRepository.save(comment);
        return convertToDTO(comment);
    }

    public List<CommentDTO> getCommentsByPost(String postUuid) {
        Post post = postRepository.findOneByUuid(postUuid);
        return post.getComments().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setUuid(comment.getUuid());
        dto.setContent(comment.getContent());
        dto.setCreatedDate(comment.getCreatedDate());
        dto.setLastModifiedDate(comment.getLastModifiedDate());
        return dto;
    }
}