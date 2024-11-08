package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.PostDTO;
import com.infsis.socialpagebackend.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@Validated
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{postUuid}")
    public PostDTO get(@PathVariable String postUuid) {
        return postService.getPost(postUuid);
    }

    @GetMapping
    public List<PostDTO> getAll() {
        return postService.getAllPost();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO create(@Valid @RequestBody PostDTO postDTO) {
        return postService.savePost(postDTO);
    }

}
