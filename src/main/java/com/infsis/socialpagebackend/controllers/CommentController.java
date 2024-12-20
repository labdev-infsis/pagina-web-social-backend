package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.CommentDTO;
import com.infsis.socialpagebackend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postUuid}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO addComment(@PathVariable String postUuid, @RequestBody CommentDTO commentDTO) {
        return commentService.saveComment(postUuid, commentDTO);
    }

    @GetMapping("/post/{postUuid}/comments")
    public List<CommentDTO> getComments(@PathVariable String postUuid) {
        return commentService.getCommentsByPost(postUuid);
    }
    @DeleteMapping("/post/{postUuid}/comments/{commentUuid}")
    public ResponseEntity<String> deleteComment(@PathVariable String postUuid, @PathVariable String commentUuid) {
        try {
            commentService.deleteComment(postUuid, commentUuid);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Comentario eliminado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    /*
    Retrieve all comments that need a moderator to be approved
     */
    @GetMapping("/comments/moderated")
    public List<CommentDTO> getAllModeratedComments() {
        return commentService.getAllPendingModeratedComments();
    }

    /*
Retrieve all comments that need a moderator to be approved
 */
    @PutMapping("/comments/approve")
    public CommentDTO approveModeratedComments(@RequestBody CommentDTO commentDTO) {
        return commentService.approvePendingModeratedComment(commentDTO);
    }
}