package com.infsis.socialpagebackend.reactions.controllers;

import com.infsis.socialpagebackend.reactions.dtos.CommentReactionDTO;
import com.infsis.socialpagebackend.reactions.services.CommentReactionService;
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
public class CommentReactionController {

    @Autowired
    private CommentReactionService commentReactionService;

    @GetMapping("/comment/{commentUuid}/reactions/{reactionUuid}")
    public CommentReactionDTO get(@PathVariable String commentUuid, @PathVariable String reactionUuid) {
        return commentReactionService.getCommentReaction(commentUuid, reactionUuid);
    }

    @GetMapping("/comment/{commentUuid}/reactions")
    public List<CommentReactionDTO> getAll(@PathVariable String commentUuid) {
        return commentReactionService.getAllCommentReaction(commentUuid);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    @PostMapping("/comment/{commentUuid}/reactions")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentReactionDTO create(@PathVariable String commentUuid, @Valid @RequestBody CommentReactionDTO commentReactionDTO) {
        return commentReactionService.saveReaction(commentUuid, commentReactionDTO);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    @PutMapping("/reactions/{reactionUuid}")
    @ResponseStatus(HttpStatus.OK)
    public CommentReactionDTO updateReaction(
            @PathVariable String reactionUuid,
            @Valid @RequestBody CommentReactionDTO commentReactionDTO) {
        return commentReactionService.updateReaction(reactionUuid, commentReactionDTO);
    }
    

}
