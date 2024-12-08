package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.GroupDTO;
import com.infsis.socialpagebackend.dtos.GroupMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.Group;
import com.infsis.socialpagebackend.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GroupService {

    public enum GroupStatus {
        CREATED, REMOVED, UPDATED
    }

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    public GroupDTO getGroup(String postGroupUuid) {
        Group group = groupRepository.findOneByUuid(postGroupUuid);
        if(group == null) {
            throw new NotFoundException("Group:", postGroupUuid);
        }
        return groupMapper.toDTO(group);
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository
                .findAll()
                .stream()
                .map(group -> groupMapper.toDTO(group))
                .collect(Collectors.toList());
    }

    public GroupDTO saveGroup(GroupDTO groupDTO) {

        Optional<Group> groupFound = groupRepository.findOneByName(groupDTO.getName());
        if (groupFound.isPresent()) {
            throw new IllegalArgumentException("Ya existe un grupo con el mismo nombre");
        }

        Group group = groupMapper.getPostGroup(groupDTO);
        group.setStatus(GroupStatus.CREATED.name());
        groupRepository.save(group);

        return groupMapper.toDTO(group);
    }

}
