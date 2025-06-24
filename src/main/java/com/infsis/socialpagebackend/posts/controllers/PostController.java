package com.infsis.socialpagebackend.posts.controllers;

import com.infsis.socialpagebackend.posts.dtos.PostDTO;
import com.infsis.socialpagebackend.posts.dtos.PostGroupDTO;
import com.infsis.socialpagebackend.posts.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Validated
public class PostController {
    @GetMapping("/{postUuid}")
    public PostDTO get(@PathVariable String postUuid) {
        return postService.getPost(postUuid);
    }

    @GetMapping
    public List<PostDTO> getAll() {
        return postService.getAllPost();
    }

    @GetMapping(params = "type")
    public ResponseEntity<List<PostDTO>> getPostsByType(@RequestParam String type) {
        List<PostDTO> posts = postService.getPostsByType(type);
        return ResponseEntity.ok(posts);
    }

    @PreAuthorize("hasAuthority('CREATE_POST')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO create(@Valid @RequestBody PostDTO postDTO) {
        return postService.savePost(postDTO);
    }

    @PreAuthorize("hasAuthority('DELETE_POST')")
    @DeleteMapping("/{postUuid}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO delete(@PathVariable String postUuid) {
        return postService.deletePost(postUuid);
    }

    @PreAuthorize("hasAuthority('GROUP_POST')")
    @PostMapping("/{postUuid}/group")
    @ResponseStatus(HttpStatus.OK)
    public PostGroupDTO group(@PathVariable String postUuid, @Valid @RequestBody PostGroupDTO postGroupDTO) {
        return postService.addToGroup(postUuid, postGroupDTO);
    }

    @PreAuthorize("hasAuthority('UNGROUP_POST')")
    @PutMapping("/{postUuid}/group")
    @ResponseStatus(HttpStatus.OK)
    public PostGroupDTO ungroup(@PathVariable String postUuid, @Valid @RequestBody PostGroupDTO postGroupDTO) {
        return postService.removeFromGroup(postUuid, postGroupDTO);
    }

    @GetMapping("/group/{groupUuid}")
    public List<PostDTO> getAllByGroup(@PathVariable String groupUuid) {
        return postService.getAllByGroup(groupUuid);
    }

    @Autowired
    private PostService postService;

    @PreAuthorize("hasAuthority('UPDATE_POST')")
    @PutMapping("/{postUuid}")
    public ResponseEntity<PostDTO> updatePost(
        @PathVariable String postUuid,
        @Valid @RequestBody PostDTO updatedPostDTO
    ) {
        PostDTO updatedPost = postService.updatePost(postUuid, updatedPostDTO);
        return ResponseEntity.ok(updatedPost);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> searchPosts(@RequestParam("text") String text) {

        List<PostDTO> posts = postService.searchPosts(text);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/paged")
public ResponseEntity<List<PostDTO>> getPagedPosts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) {
    List<PostDTO> posts = postService.getPagedPosts(page, size);
    return ResponseEntity.ok(posts);
}


}
