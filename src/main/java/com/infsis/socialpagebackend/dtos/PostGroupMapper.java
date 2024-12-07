package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.EmojiType;
import com.infsis.socialpagebackend.models.PostGroup;
import org.springframework.stereotype.Component;

@Component
public class PostGroupMapper {

    public PostGroupDTO toDTO(PostGroup postGroup) {
        PostGroupDTO postGroupDTO = new PostGroupDTO();
        postGroupDTO.setUuid(postGroupDTO.getUuid());
        postGroupDTO.setName(postGroup.getName());
        postGroupDTO.setStatus(postGroup.getStatus());

        return postGroupDTO;
    }

    public PostGroup getPostGroup(PostGroupDTO postGroupDTO) {
        PostGroup postGroup = new PostGroup();

        postGroup.setUuid(postGroupDTO.getUuid());
        postGroup.setName(postGroupDTO.getName());
        postGroup.setStatus(postGroupDTO.getStatus());

        return postGroup;
    }

}
