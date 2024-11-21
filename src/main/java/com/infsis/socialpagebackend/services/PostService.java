package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.*;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.*;
import com.infsis.socialpagebackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostService {

    private static final String SORT_LAST_TO_OLD = "last_to_old";

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
    private ContentMapper contentMapper;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CommentConfigRepository commentConfigRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Obtiene un post específico por UUID.
     */
    public PostDTO getPost(String postUuid) {
        Post post = postRepository.findOneByUuid(postUuid);
        if (post == null) {
            throw new NotFoundException("Post", postUuid);
        }
        return postMapper.toDTO(post);
    }

    /**
     * Obtiene todos los posts.
     */
    public List<PostDTO> getAllPost() {
        return postRepository
                .findAll()
                .stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Guarda un nuevo post.
     */
    public PostDTO savePost(PostDTO postDTO) {
        // Crear contenido y asociarlo al post
        Content content = new Content();

        // Validar y cargar entidades relacionadas
        CommentConfig commentConfig = commentConfigRepository.findOneByUuid(postDTO.getComment_config_id());
        Institution institution = institutionRepository.findOneByUuid(postDTO.getInstitution_id());
        Users users = userRepository.findOneByUuid(postDTO.getUser_id());

        if (commentConfig == null || institution == null || users == null) {
            throw new NotFoundException("Some referenced entities were not found.");
        }

        // Guardar media y texto asociados al contenido
        List<Media> medias = saveMedia(postDTO.getContent(), content);
        Text text = saveText(postDTO.getContent(), content);

        content.setText(text);
        content.setMedia(medias);
        contentRepository.save(content);

        // Crear y guardar el post
        Post post = postMapper.getPost(postDTO, content, commentConfig, institution, users);
        postRepository.save(post);

        // Asociar el post con su contenido
        content.setPost(post);
        contentRepository.save(content);

        return postMapper.toDTO(post);
    }

    /**
     * Guarda media asociada al contenido.
     */
    private List<Media> saveMedia(ContentDTO contentDTO, Content content) {
        if (contentDTO.getMedia() == null) {
            return new ArrayList<>();
        }
        return contentDTO.getMedia()
                .stream()
                .map(media -> mediaMapper.getMedia(media, content))
                .peek(mediaRepository::save)
                .collect(Collectors.toList());
    }

    /**
     * Guarda texto asociado al contenido.
     */
    private Text saveText(ContentDTO contentDTO, Content content) {
        if (contentDTO.getText() == null) {
            return null;
        }
        Text text = new Text();
        text.setText(contentDTO.getText());
        text.setContent(content);
        return textRepository.save(text);
    }

    /**
     * Obtiene posts con paginación.
     */
    public List<PostDTO> getPosts(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(SORT_LAST_TO_OLD.equals(sort) ? "created_date" : "id").descending());
        Page<Post> postPage = postRepository.findAll(pageable);

        // Filtrar publicaciones con datos completos
        return postPage.stream()
                .filter(post -> post.getPost_date() != null) // Filtra registros inválidos
                .map(postMapper::toDTO) // Usa PostMapper para la conversión
                .collect(Collectors.toList());
    }

    /**
     * Obtiene publicaciones por grupo.
     */
    public List<PostDTO> getPostsByGroupUuid(String groupUuid) {
        List<Post> posts = postRepository.findByGroupUuid(groupUuid);
        if (posts.isEmpty()) {
            throw new NotFoundException("Group", groupUuid);
        }
        return posts.stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }
}
