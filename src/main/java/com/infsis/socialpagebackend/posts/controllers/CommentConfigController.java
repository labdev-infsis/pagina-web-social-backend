package com.infsis.socialpagebackend.posts.controllers;

import com.infsis.socialpagebackend.posts.dtos.CommentConfigDTO;
import com.infsis.socialpagebackend.posts.services.CommentConfigService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment-config")
@Validated
public class CommentConfigController {

    @Autowired
    private CommentConfigService commentConfigService;

    //@PreAuthorize("hasAuthority('VIEW_COMMENT_CONFIG')")
    @GetMapping("/{commentConfigUuid}")
    public CommentConfigDTO get(@PathVariable String commentConfigUuid) {
        return commentConfigService.getCommentConfig(commentConfigUuid);
    }

    //@PreAuthorize("hasAuthority('VIEW_ALL_COMMENT_CONFIGS')")
    @GetMapping
    public List<CommentConfigDTO> getAll() {
        return commentConfigService.getAllCommentConfig();
    }

    @PreAuthorize("hasAuthority('CREATE_COMMENT_CONFIG')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentConfigDTO create(@Valid @RequestBody CommentConfigDTO commentConfigDTO) {
        return commentConfigService.saveCommentConfig(commentConfigDTO);
    }
}