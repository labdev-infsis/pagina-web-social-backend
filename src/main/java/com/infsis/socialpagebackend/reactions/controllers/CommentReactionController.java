package com.infsis.socialpagebackend.reactions.controllers;

import com.infsis.socialpagebackend.reactions.dtos.CommentReactionDTO;
import com.infsis.socialpagebackend.reactions.services.CommentReactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PreAuthorize("hasAuthority('CREATE_COMMENT_REACTION')")
    @PostMapping("/comment/{commentUuid}/reactions")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentReactionDTO create(@PathVariable String commentUuid, @Valid @RequestBody CommentReactionDTO commentReactionDTO) {
        return commentReactionService.saveReaction(commentUuid, commentReactionDTO);
    }

    @PreAuthorize("hasAuthority('DELETE_COMMENT_REACTION')")
    @DeleteMapping("/comment/{commentUuid}/reactions")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String commentUuid) {
        Map<String, String> response = commentReactionService.deleteReaction(commentUuid);
        return ResponseEntity.ok(response);
    }
}