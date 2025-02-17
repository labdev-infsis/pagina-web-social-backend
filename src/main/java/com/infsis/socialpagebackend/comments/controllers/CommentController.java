package com.infsis.socialpagebackend.comments.controllers;

import com.infsis.socialpagebackend.comments.dtos.CommentDTO;
import com.infsis.socialpagebackend.comments.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    @PostMapping("/post/{postUuid}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO addComment(@PathVariable String postUuid, @RequestBody CommentDTO commentDTO) {
        return commentService.saveComment(postUuid, commentDTO);
    }

    @GetMapping("/posts/{postUuid}/comments")
    public List<CommentDTO> getComments(@PathVariable String postUuid) {
        return commentService.getCommentsByPost(postUuid);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
    @DeleteMapping("/posts/{postUuid}/comments/{commentUuid}")
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
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/comments/moderated")
    public List<CommentDTO> getAllModeratedComments() {
        return commentService.getAllPendingModeratedComments();
    }

    /*
    Retrieve all the rejected comments by a moderated
    */
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/comments/rejected")
    public List<CommentDTO> getAllRejectedComments() {
        return commentService.getAllRejectedModeratedComments();
    }

    /*
    It changes comment state PENDING to APPROVED
    */
    @PreAuthorize("hasRole('MODERATOR')")
    @PutMapping("/comments/approve")
    public CommentDTO approveModeratedComments(@RequestBody CommentDTO commentDTO) {
        return commentService.approvePendingModeratedComment(commentDTO);
    }

    /*
    It changes comment state PENDING to REJECTED
    */
    @PreAuthorize("hasRole('MODERATOR')")
    @PutMapping("/comments/reject")
    public CommentDTO rejectModeratedComments(@RequestBody CommentDTO commentDTO) {
        return commentService.rejectPendingModeratedComment(commentDTO);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @PutMapping("/comments/delete")
    public CommentDTO deleteModeratedComments(@RequestBody CommentDTO commentDTO) {
        return commentService.removeModeratedComment(commentDTO);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/comments/deleted")
    public List<CommentDTO> getAllDeletedComments() {
        return commentService.getAllDeletedModeratedComments();
    }

}