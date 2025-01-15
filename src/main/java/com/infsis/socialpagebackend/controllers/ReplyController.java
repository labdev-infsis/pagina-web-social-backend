package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.ReplyDTO;
import com.infsis.socialpagebackend.repositories.CommentRepository;
import com.infsis.socialpagebackend.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comments/{comment_uuid}/replies")
    public List<ReplyDTO> getRepliesByCommentUuid(@PathVariable String comment_uuid) {
        return replyService.getRepliesByCommentUuid(comment_uuid);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    @PostMapping("/comments/{comment_uuid}/replies")
    public ReplyDTO createReply(@PathVariable String comment_uuid,@RequestBody ReplyDTO replyRequest) {
        return replyService.saveReply(comment_uuid,replyRequest);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    @DeleteMapping("/replies/{reply_uuid}")
    public void deleteReply(@PathVariable String reply_uuid) {
        replyService.deleteReply(reply_uuid);
    }
}