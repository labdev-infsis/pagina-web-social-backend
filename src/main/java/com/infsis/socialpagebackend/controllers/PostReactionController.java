package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.PostReactionDTO;
import com.infsis.socialpagebackend.services.PostReactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Validated
public class PostReactionController {

    @Autowired
    private PostReactionService postReactionService;

    @GetMapping("/posts/{postUuid}/reactions/{reactionUuid}")
    public PostReactionDTO get(@PathVariable String postUuid, @PathVariable String reactionUuid) {
        return postReactionService.getPostReaction(postUuid, reactionUuid);
    }

    @GetMapping("/posts/{postUuid}/reactions")
    public List<PostReactionDTO> getAll(@PathVariable String postUuid) {
        return postReactionService.getAllPostReaction(postUuid);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/posts/{postUuid}/reactions")
    @ResponseStatus(HttpStatus.CREATED)
    public PostReactionDTO create(@PathVariable String postUuid, @Valid @RequestBody PostReactionDTO postReactionDTO) {
        return postReactionService.saveReaction(postUuid, postReactionDTO);
    }

}
