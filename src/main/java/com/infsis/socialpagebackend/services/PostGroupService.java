package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.CommentConfigDTO;
import com.infsis.socialpagebackend.dtos.CommentConfigMapper;
import com.infsis.socialpagebackend.dtos.PostGroupDTO;
import com.infsis.socialpagebackend.dtos.PostGroupMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.CommentConfig;
import com.infsis.socialpagebackend.models.PostGroup;
import com.infsis.socialpagebackend.repositories.CommentConfigRepository;
import com.infsis.socialpagebackend.repositories.PostGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostGroupService {

    @Autowired
    private PostGroupRepository postGroupRepository;

    @Autowired
    private PostGroupMapper postGroupMapper;

    public PostGroupDTO getPostGroup(String postGroupUuid) {
        PostGroup postGroup = postGroupRepository.findOneByUuid(postGroupUuid);
        if(postGroup == null) {
            throw new NotFoundException("PostGroup:", postGroupUuid);
        }
        return postGroupMapper.toDTO(postGroup);
    }

    public List<PostGroupDTO> getAllPostGroup() {
        return postGroupRepository
                .findAll()
                .stream()
                .map(postGroup -> postGroupMapper.toDTO(postGroup))
                .collect(Collectors.toList());
    }

    public PostGroupDTO savePostGroup(PostGroupDTO postGroupDTO) {

        PostGroup postGroup = postGroupMapper.getPostGroup(postGroupDTO);
        postGroupRepository.save(postGroup);

        return postGroupMapper.toDTO(postGroup);
    }

}
