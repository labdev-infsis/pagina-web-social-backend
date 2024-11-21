package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.PostDTO;
import com.infsis.socialpagebackend.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define esta clase como un controlador REST
@RequestMapping("/api/posts") // Ruta base para este controlador
@Validated // Valida automáticamente las anotaciones @Valid
public class PostController {

    @Autowired
    private PostService postService;

    // Endpoint para obtener un post específico por UUID
    @GetMapping("/{postUuid}")
    public PostDTO get(@PathVariable String postUuid) {
        return postService.getPost(postUuid); // Llama al servicio para obtener el post
    }

    // Endpoint para obtener todos los posts
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "last_to_old") String sort
    ) {
        System.out.println("Entrando al endpoint GET /api/posts");
        List<PostDTO> posts = postService.getPosts(page, size, sort);
        return ResponseEntity.ok(posts);
    }
    

    // Endpoint para crear un nuevo post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Define el estado HTTP como 201 (Creado)
    public PostDTO create(@Valid @RequestBody PostDTO postDTO) {
        return postService.savePost(postDTO); // Llama al servicio para guardar el post
    }

    // Endpoint para obtener posts por grupo (por ejemplo, destacados)
    @GetMapping("/group/{uuid}")
    public List<PostDTO> getPostsByGroupUuid(@PathVariable String uuid) {
        return postService.getPostsByGroupUuid(uuid);
    }
}

