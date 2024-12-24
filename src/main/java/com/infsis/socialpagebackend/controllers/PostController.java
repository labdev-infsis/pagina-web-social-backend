package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.MediaItemDTO;
import com.infsis.socialpagebackend.dtos.PostDTO;
import com.infsis.socialpagebackend.dtos.PostGroupDTO;
import com.infsis.socialpagebackend.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Validated
public class PostController {
    public static final String IMAGE = "image";
    public static final String VIDEO = "video";
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

    @PostMapping("/{postUuid}/group")
    @ResponseStatus(HttpStatus.OK)
    public PostGroupDTO group(@PathVariable String postUuid, @Valid @RequestBody PostGroupDTO postGroupDTO) {
        return postService.addToGroup(postUuid, postGroupDTO);
    }

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

    @PutMapping("/{postUuid}") // Este endpoint maneja solicitudes PUT para actualizar una publicación específica
    public ResponseEntity<PostDTO> updatePost(
        @PathVariable String postUuid, // Se obtiene el UUID de la publicación desde la URL
        @Valid @RequestBody PostDTO updatedPostDTO // El cuerpo de la solicitud contiene los datos actualizados
    ) {
        // Llamamos al servicio para actualizar la publicación y devolvemos la respuesta con el objeto actualizado
        PostDTO updatedPost = postService.updatePost(postUuid, updatedPostDTO);
        return ResponseEntity.ok(updatedPost); // Respondemos con un código HTTP 200 (OK)
    }

   /**
     * Endpoint para buscar publicaciones por texto.
     *
     * @param text Texto para buscar.
     * @return Lista de publicaciones encontradas.
     */
    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> searchPosts(@RequestParam("text") String text) {
        // Llamamos al servicio para buscar publicaciones
        List<PostDTO> posts = postService.searchPosts(text);

        // Devolvemos la respuesta con los resultados
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/institution/photos")
    public List<MediaItemDTO> getPhotosByInstitution() {
        return postService.getMediasInstitution(IMAGE);
    }
    @GetMapping("/institution/videos")
    public List<MediaItemDTO> getVideosByInstitution() {
        return postService.getMediasInstitution(VIDEO);
    }

}
