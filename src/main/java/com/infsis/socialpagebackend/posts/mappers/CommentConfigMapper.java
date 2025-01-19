package com.infsis.socialpagebackend.posts.mappers;

import com.infsis.socialpagebackend.posts.models.CommentConfig;
import com.infsis.socialpagebackend.posts.dtos.CommentConfigDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentConfigMapper {

    public CommentConfigDTO toDTO(CommentConfig commentConfig) {
        CommentConfigDTO commentConfigDTO = new CommentConfigDTO();
        commentConfigDTO.setUuid(commentConfig.getUuid());
        commentConfigDTO.setName(commentConfig.getName());
        commentConfigDTO.setConfiguration_type(commentConfig.getConfiguration_type());

        return commentConfigDTO;
    }

    public CommentConfig getCommentConfig(CommentConfigDTO commentConfigDTO) {
        CommentConfig commentConfig = new CommentConfig();
        commentConfig.setUuid(commentConfigDTO.getUuid());
        commentConfig.setName(commentConfigDTO.getName());
        commentConfig.setConfiguration_type(commentConfigDTO.getConfiguration_type());

        return commentConfig;
    }
}
