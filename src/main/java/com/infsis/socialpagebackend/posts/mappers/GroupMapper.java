package com.infsis.socialpagebackend.posts.mappers;

import com.infsis.socialpagebackend.posts.models.Group;
import com.infsis.socialpagebackend.posts.dtos.GroupDTO;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {



    public GroupDTO toDTO(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setUuid(group.getUuid());
        groupDTO.setName(group.getName());
        groupDTO.setStatus(group.getStatus());

        return groupDTO;
    }

    public Group getPostGroup(GroupDTO groupDTO) {
        Group group = new Group();

        group.setUuid(groupDTO.getUuid());
        group.setName(groupDTO.getName());
        group.setStatus(groupDTO.getStatus());

        return group;
    }

}
