package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.ReplyReactionDTO;
import com.infsis.socialpagebackend.services.ReplyReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reply-reactions")
public class ReplyReactionController {

    @Autowired
    private ReplyReactionService replyReactionService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/{replyUuid}")
    public ResponseEntity<ReplyReactionDTO> createReplyReaction(
            @PathVariable String replyUuid,
            @RequestBody ReplyReactionDTO replyReactionDTO) {
        ReplyReactionDTO createdReaction = replyReactionService.saveReplyReaction(replyUuid, replyReactionDTO);
        return ResponseEntity.ok(createdReaction);
    }

    @GetMapping("/{replyUuid}")
    public ResponseEntity<List<ReplyReactionDTO>> getAllReplyReactions(@PathVariable String replyUuid) {
        List<ReplyReactionDTO> reactions = replyReactionService.getAllReplyReactions(replyUuid);
        return ResponseEntity.ok(reactions);
    }

    @GetMapping("/{replyUuid}/{reactionUuid}")
    public ResponseEntity<ReplyReactionDTO> getReplyReaction(
            @PathVariable String replyUuid,
            @PathVariable String reactionUuid) {
        ReplyReactionDTO reaction = replyReactionService.getReplyReaction(replyUuid, reactionUuid);
        return ResponseEntity.ok(reaction);
    }
}