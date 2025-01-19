package com.infsis.socialpagebackend.posts.mappers;

import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.comments.dtos.CommentCounterDTO;
import com.infsis.socialpagebackend.posts.dtos.ContentDTO;
import com.infsis.socialpagebackend.posts.dtos.ReactionCounterDTO;
import com.infsis.socialpagebackend.institutions.models.Institution;
import com.infsis.socialpagebackend.posts.dtos.PostDTO;
import com.infsis.socialpagebackend.posts.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostMapper {

    @Autowired
    public ContentMapper contentMapper;

    public PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setUuid(post.getUuid());
        postDTO.setInstitution_id(post.getInstitution().getUuid());
        postDTO.setUser_id(post.getUser().getUuid());
        postDTO.setComment_config_id(post.getComment_conf().getUuid());
        postDTO.setDate(post.getPost_date());
        postDTO.setContent(contentMapper.toDTO(post.getContent()));
  

        return postDTO;
    }

    public PostDTO toDTO(Post post, ReactionCounterDTO reactionCounterDTO, CommentCounterDTO commentCounterDTO) {
        PostDTO postDTO = new PostDTO();
        postDTO.setUuid(post.getUuid());
        postDTO.setInstitution_id(post.getInstitution().getUuid());
        postDTO.setUser_id(post.getUser().getUuid());
        postDTO.setComment_config_id(post.getComment_conf().getUuid());
        postDTO.setDate(post.getPost_date());
        postDTO.setContent(contentMapper.toDTO(post.getContent()));
        postDTO.setReactions(reactionCounterDTO);
        postDTO.setCommentCounter(commentCounterDTO);

        return postDTO;
    }

    public Post getPost(PostDTO postDTO, Content content,
                        CommentConfig commentConfig,
                        Institution institution, Users user) {
        Post post = new Post();

        post.setInstitution(institution);
        post.setUser(user);
        post.setComment_conf(commentConfig);
        post.setPost_date(postDTO.getDate());
        post.setContent(content);
        return post;
    }


       // Método para actualizar el contenido de una publicación
       public Content getContent(ContentDTO contentDTO, Content existingContent) {
        if (contentDTO.getText() != null) {
            Text text = new Text(); // Instancia un nuevo objeto de tipo Text
            text.setText(contentDTO.getText()); // Asigna el valor del texto desde el DTO
            existingContent.setText(text); // Asigna el objeto Text a la entidad Content
        }
        return existingContent; // Devuelve la entidad actualizada
    }
    


}
