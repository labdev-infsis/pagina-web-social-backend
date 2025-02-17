package com.infsis.socialpagebackend.comments.mappers;

import com.infsis.socialpagebackend.comments.dtos.CommentDTO;
import com.infsis.socialpagebackend.enums.CommentState;
import com.infsis.socialpagebackend.comments.models.Comment;
import com.infsis.socialpagebackend.posts.models.Post;
import com.infsis.socialpagebackend.authentication.models.Users;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUuid(comment.getUuid());
        commentDTO.setContent(comment.getContent());
        commentDTO.setUserId(comment.getUser().getUuid());
        commentDTO.setModerated(comment.isModerated());
        commentDTO.setState(comment.getState());
        commentDTO.setDate(comment.getComment_date());
        commentDTO.setUser_name(comment.getUser().getName() + " " + comment.getUser().getLastName());
        commentDTO.setUser_photo(comment.getUser().getPhoto_profile_path());

        return commentDTO;
    }
    

    public Comment getComment(CommentDTO commentDTO, Users user, Post post) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setUser(user);
        comment.setPost(post);
        comment.setComment_date(commentDTO.getDate());
        comment.setState(CommentState.VISIBLE.name());
        comment.setModerated(commentDTO.isModerated());
        return comment;
    }

}
