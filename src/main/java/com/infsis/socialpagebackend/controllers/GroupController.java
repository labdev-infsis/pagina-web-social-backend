package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.CommentConfigDTO;
import com.infsis.socialpagebackend.dtos.PostGroupDTO;
import com.infsis.socialpagebackend.services.PostGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post-group")
public class GroupController {

    @Autowired
    private PostGroupService postGroupService;

    @GetMapping("/{postGroupUuid}")
    public PostGroupDTO get(@PathVariable String postGroupUuid) {
        return postGroupService.getCommentConfig(postGroupUuid);
    }

    @GetMapping
    public List<PostGroupDTO> getAll() {
        return postGroupService.getAllCommentConfig();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostGroupDTO create(@Valid @RequestBody PostGroupDTO postGroupDTO) {
        return postGroupService.saveCommentConfig(postGroupDTO);
    }


}
