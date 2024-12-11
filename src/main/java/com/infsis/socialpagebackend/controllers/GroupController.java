package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.GroupDTO;
import com.infsis.socialpagebackend.services.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/{groupUuid}")
    public GroupDTO get(@PathVariable String groupUuid) {
        return groupService.getGroup(groupUuid);
    }

    @GetMapping
    public List<GroupDTO> getAll() {
        return groupService.getAllGroups();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupDTO create(@Valid @RequestBody GroupDTO groupDTO) {
        return groupService.saveGroup(groupDTO);
    }

}
