package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.CommentConfigDTO;
import com.infsis.socialpagebackend.dtos.CommentConfigMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.CommentConfig;
import com.infsis.socialpagebackend.repositories.CommentConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentConfigService {

    @Autowired
    private CommentConfigRepository commentConfigRepository;

    @Autowired
    private CommentConfigMapper commentConfigMapper;

    public CommentConfigDTO getCommentConfig(String commentConfigUuid) {
        CommentConfig commentConfig = commentConfigRepository.findOneByUuid(commentConfigUuid);
        if(commentConfig == null) {
            throw new NotFoundException("CommentConfig:", commentConfigUuid);
        }
        return commentConfigMapper.toDTO(commentConfig);
    }

    public List<CommentConfigDTO> getAllCommentConfig() {
        return commentConfigRepository
                .findAll()
                .stream()
                .map(commentConfig -> commentConfigMapper.toDTO(commentConfig))
                .collect(Collectors.toList());
    }

    public CommentConfigDTO saveCommentConfig(CommentConfigDTO commentConfigDTO) {

        CommentConfig commentConfig = commentConfigMapper.getCommentConfig(commentConfigDTO);
        commentConfigRepository.save(commentConfig);

        return commentConfigMapper.toDTO(commentConfig);
    }
}
