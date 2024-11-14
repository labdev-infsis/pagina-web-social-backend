package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.CommentDTO;
import com.infsis.socialpagebackend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postUuid}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO addComment(@PathVariable String postUuid, @RequestBody CommentDTO commentDTO) {
        return commentService.saveComment(postUuid, commentDTO);
    }

    @GetMapping("/{postUuid}")
    public List<CommentDTO> getComments(@PathVariable String postUuid) {
        return commentService.getCommentsByPost(postUuid);
    }
}