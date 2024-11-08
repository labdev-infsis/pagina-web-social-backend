package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        postDTO.setDate(post.getDate());
        postDTO.setContent(contentMapper.toDTO(post.getContent()));

        return postDTO;
    }

    public Post getPost(PostDTO postDTO, Content content,
                        CommentConfig commentConfig,
                        Institution institution, Users user) {
        Post post = new Post();

        post.setInstitution(institution);
        post.setUser(user);
        post.setComment_conf(commentConfig);
        post.setDate(postDTO.getDate());
        post.setContent(content);
        return post;
    }
}
