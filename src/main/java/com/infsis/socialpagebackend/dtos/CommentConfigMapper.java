package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.CommentConfig;
import org.springframework.stereotype.Component;

@Component
public class CommentConfigMapper {

    public CommentConfigDTO toDTO(CommentConfig commentConfig) {
        CommentConfigDTO commentConfigDTO = new CommentConfigDTO();
        commentConfigDTO.setUuid(commentConfig.getUuid());
        commentConfigDTO.setConfiguration(commentConfig.getConfiguration());

        return commentConfigDTO;
    }

    public CommentConfig getCommentConfig(CommentConfigDTO commentConfigDTO) {
        CommentConfig commentConfig = new CommentConfig();
        commentConfig.setUuid(commentConfigDTO.getUuid());
        commentConfig.setConfiguration(commentConfigDTO.getConfiguration());

        return commentConfig;
    }
}
