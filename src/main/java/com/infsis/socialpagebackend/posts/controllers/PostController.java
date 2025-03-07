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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO create(@Valid @RequestBody PostDTO postDTO) {
        return postService.savePost(postDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{postUuid}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO delete(@PathVariable String postUuid) {
        return postService.deletePost(postUuid);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{postUuid}/group")
    @ResponseStatus(HttpStatus.OK)
    public PostGroupDTO group(@PathVariable String postUuid, @Valid @RequestBody PostGroupDTO postGroupDTO) {
        return postService.addToGroup(postUuid, postGroupDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
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
    private PostService postService; // Inyección del servicio que contiene la lógica de negocio

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{postUuid}") // Este endpoint maneja solicitudes PUT para actualizar una publicación específica
    public ResponseEntity<PostDTO> updatePost(
        @PathVariable String postUuid, // Se obtiene el UUID de la publicación desde la URL
        @Valid @RequestBody PostDTO updatedPostDTO // El cuerpo de la solicitud contiene los datos actualizados
    ) {
        // Llamamos al servicio para actualizar la publicación y devolvemos la respuesta con el objeto actualizado
        PostDTO updatedPost = postService.updatePost(postUuid, updatedPostDTO);
        return ResponseEntity.ok(updatedPost); // Respondemos con un código HTTP 200 (OK)
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> searchPosts(@RequestParam("text") String text) {
        // Llamamos al servicio para buscar publicaciones
        List<PostDTO> posts = postService.searchPosts(text);

        // Devolvemos la respuesta con los resultados
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
