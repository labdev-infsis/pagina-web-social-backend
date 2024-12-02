package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.*;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.*;
import com.infsis.socialpagebackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;
    

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CommentConfigRepository commentConfigRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private UserRepository userRepository;



    public PostDTO getPost(String postUuid) {
        Post post = postRepository.findOneByUuid(postUuid);
        if(post == null) {
            throw new NotFoundException("Post", postUuid);
        }
        return postMapper.toDTO(post);
    }

    public List<PostDTO> getAllPost() {
        return postRepository
                .findAll()
                .stream()
                .map(post -> postMapper.toDTO(post))
                .collect(Collectors.toList());
    }

    public PostDTO savePost(PostDTO postDTO) {

        Content content = contentRepository.save(new Content());

        CommentConfig commentConfig = commentConfigRepository.findOneByUuid(postDTO.getComment_config_id());
        Institution institution = institutionRepository.findOneByUuid(postDTO.getInstitution_id());
        Users users = userRepository.findOneByUuid(postDTO.getUser_id());

        Post post = new Post();
        PostDTO resDTO = new PostDTO();
        if (commentConfig != null && institution != null & users != null) {
            List<Media> medias = saveMedia(postDTO.getContent(), content);
            Text text = saveText(postDTO.getContent(), content);

            content.setText(text);
            content.setMedia(medias);

            post = postMapper.getPost(postDTO, content, commentConfig, institution, users);
            postRepository.save(post);

            content.setPost(post);
            contentRepository.save(content);

            resDTO = postMapper.toDTO(post);

        }
        return resDTO;
    }

    private List<Media> saveMedia(ContentDTO contentDTO, Content content){
        List<Media> medias = new ArrayList<>();
        if(contentDTO.getMedia() != null ) {
            medias = contentDTO.getMedia()
                    .stream()
                    .map(media -> mediaMapper.getMedia(media, content))
                    .collect(Collectors.toList());

            medias.stream().forEach(
                    (media) -> mediaRepository.save(media)
            );
        }
        return medias;
    }

    private Text saveText(ContentDTO contentDTO, Content content) {
        Text text = new Text();
        if(contentDTO.getText() != null) {
            text.setText(contentDTO.getText());
            text.setContent(content);
            textRepository.save(text);
        }
        return text;
    }


    // Método para actualizar una publicación específica
    public PostDTO updatePost(String postUuid, PostDTO updatedPostDTO) {
        // Buscamos la publicación en la base de datos utilizando su UUID
        Post existingPost = postRepository.findOneByUuid(postUuid);
        if (existingPost == null) { // Si no existe, lanzamos una excepción personalizada
            throw new NotFoundException("Post", postUuid);
        }

   
        // Si el contenido está presente en el DTO, actualizamos el contenido asociado
        if (updatedPostDTO.getContent() != null) {
            Content updatedContent = contentRepository.save(
                postMapper.getContent(updatedPostDTO.getContent(), existingPost.getContent())
            );
            existingPost.setContent(updatedContent); // Asociamos el contenido actualizado a la publicación
        }

        // Actualizamos la configuración de comentarios si se envía un nuevo valor
        if (updatedPostDTO.getComment_config_id() != null) {
            existingPost.getComment_conf().setUuid(updatedPostDTO.getComment_config_id());
        }

        // Guardamos la publicación actualizada en la base de datos
        Post updatedPost = postRepository.save(existingPost);

        // Convertimos la publicación actualizada en un DTO para devolverla como respuesta
        return postMapper.toDTO(updatedPost);
    }
  // Método para buscar publicaciones por texto
  public List<PostDTO> searchPosts(String text) {
    // Buscar publicaciones por texto en el repositorio
    List<Post> posts = postRepository.searchPostsByText(text);

    // Convertir las publicaciones encontradas a una lista de DTOs
    return posts.stream()
            .map(post -> postMapper.toDTO(post))
            .collect(Collectors.toList());
}


}
