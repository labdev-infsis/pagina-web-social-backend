package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.CommentConfig;
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
