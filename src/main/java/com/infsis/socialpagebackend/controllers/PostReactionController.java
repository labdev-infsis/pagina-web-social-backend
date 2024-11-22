package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.PostReactionDTO;
import com.infsis.socialpagebackend.services.PostReactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Validated
public class PostReactionController {

    @Autowired
    private PostReactionService postReactionService;

    @GetMapping("/post/{postUuid}/reactions/{reactionUuid}")
    public PostReactionDTO get(@PathVariable String postUuid, @PathVariable String reactionUuid) {
        return postReactionService.getPostReaction(postUuid, reactionUuid);
    }

    @GetMapping("/post/{postUuid}/reactions")
    public List<PostReactionDTO> getAll(@PathVariable String postUuid) {
        return postReactionService.getAllPostReaction(postUuid);
    }

    @PostMapping("/post/{postUuid}/reactions")
    @ResponseStatus(HttpStatus.CREATED)
    public PostReactionDTO create(@PathVariable String postUuid, @Valid @RequestBody PostReactionDTO postReactionDTO) {
        return postReactionService.saveReaction(postUuid, postReactionDTO);
    }

}
