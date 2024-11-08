package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.CommentConfigDTO;
import com.infsis.socialpagebackend.services.CommentConfigService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment-config")
@Validated
public class CommentConfigController {

    @Autowired
    private CommentConfigService commentConfigService;

    @GetMapping("/{commentConfigUuid}")
    public CommentConfigDTO get(@PathVariable String commentConfigUuid) {
        return commentConfigService.getCommentConfig(commentConfigUuid);
    }

    @GetMapping
    public List<CommentConfigDTO> getAll() {
        return commentConfigService.getAllCommentConfig();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentConfigDTO create(@Valid @RequestBody CommentConfigDTO commentConfigDTO) {
        return commentConfigService.saveCommentConfig(commentConfigDTO);
    }
}
