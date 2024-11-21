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
        postDTO.setInstitution_id(post.getInstitution() != null ? post.getInstitution().getUuid() : null);
        postDTO.setUser_id(post.getUser() != null ? post.getUser().getUuid() : null);
        postDTO.setComment_config_id(post.getComment_conf() != null ? post.getComment_conf().getUuid() : null);
        postDTO.setPostDate(post.getPost_date());
        postDTO.setTitle(post.getTitle());
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
        post.setPost_date(postDTO.getPostDate());
        post.setContent(content);
        return post;
    }
}
