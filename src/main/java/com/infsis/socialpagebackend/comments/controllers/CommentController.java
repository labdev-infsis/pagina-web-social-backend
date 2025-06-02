package com.infsis.socialpagebackend.comments.controllers;

import com.infsis.socialpagebackend.comments.dtos.CommentDTO;
import com.infsis.socialpagebackend.comments.services.CommentService;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
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

    @PreAuthorize("hasAuthority('ADD_COMMENT')")
    @PostMapping("/post/{postUuid}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO addComment(@PathVariable String postUuid, @RequestBody CommentDTO commentDTO) {
        return commentService.saveComment(postUuid, commentDTO);
    }

    @GetMapping("/posts/{postUuid}/comments")
    public List<CommentDTO> getComments(@PathVariable String postUuid) {
        return commentService.getCommentsByPost(postUuid);
    }

    @PreAuthorize("hasAuthority('DELETE_COMMENT')")
    @DeleteMapping("/posts/{postUuid}/comments/{commentUuid}")
    public ResponseEntity<String> deleteComment(@PathVariable String postUuid, @PathVariable String commentUuid) {
        try {
            commentService.deleteComment(postUuid, commentUuid);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Comentario eliminado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('VIEW_MODERATED_COMMENTS')")
    @GetMapping("/comments/moderated")
    public List<CommentDTO> getAllModeratedComments() {
        return commentService.getAllPendingModeratedComments();
    }

    @PreAuthorize("hasAuthority('VIEW_REJECTED_COMMENTS')")
    @GetMapping("/comments/rejected")
    public List<CommentDTO> getAllRejectedComments() {
        return commentService.getAllRejectedModeratedComments();
    }

    @PreAuthorize("hasAuthority('APPROVE_COMMENT')")
    @PutMapping("/comments/approve")
    public CommentDTO approveModeratedComments(@RequestBody CommentDTO commentDTO) {
        return commentService.approvePendingModeratedComment(commentDTO);
    }

    @PreAuthorize("hasAuthority('REJECT_COMMENT')")
    @PutMapping("/comments/reject")
    public CommentDTO rejectModeratedComments(@RequestBody CommentDTO commentDTO) {
        return commentService.rejectPendingModeratedComment(commentDTO);
    }

    @PreAuthorize("hasAuthority('DELETE_MODERATED_COMMENT')")
    @PutMapping("/comments/delete")
    public CommentDTO deleteModeratedComments(@RequestBody CommentDTO commentDTO) {
        return commentService.removeModeratedComment(commentDTO);
    }

    @PreAuthorize("hasAuthority('VIEW_DELETED_COMMENTS')")
    @GetMapping("/comments/deleted")
    public List<CommentDTO> getAllDeletedComments() {
        return commentService.getAllDeletedModeratedComments();
    }

    @PreAuthorize("hasAuthority('VIEW_MODERATED_COMMENTS')")
    @GetMapping("/comments/count-moderated")
    public ResponseEntity<Long> countModeratedComments() {
        try {
            long totalModeratedComments = commentService.countModeratedComments();
            return ResponseEntity.ok(totalModeratedComments);
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}