package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.*;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.*;
import com.infsis.socialpagebackend.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PostService {

    private static final String ANONYMOUS_USER = "anonymousUser";

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

    @Autowired
    private PostReactionRepository postReactionRepository;

    @Autowired
    private EmojiTypeRepository emojiTypeRepository;

    @Autowired
    private CommentRepository commentRepository;

    public PostDTO getPost(String postUuid) {
        Post post = postRepository.findOneByUuid(postUuid);

        if(post == null) {
            throw new NotFoundException("Post", postUuid);
        }
        ReactionCounterDTO reactionCounterDTO = getPostReactionCounterDTO(post);

        CommentCounterDTO commentCounterDTO = getCommentCounter(postUuid);

        return postMapper.toDTO(post, reactionCounterDTO, commentCounterDTO);
    }

    public List<PostDTO> getAllPost() {
        return postRepository
                .findAll()
                .stream()
                .map(post -> postMapper.toDTO(post, getPostReactionCounterDTO(post), getCommentCounter(post.getUuid())))
                .collect(Collectors.toList());
    }

    public PostDTO savePost(PostDTO postDTO) {

        Content content = contentRepository.save(new Content());

        CommentConfig commentConfig = commentConfigRepository.findOneByUuid(postDTO.getComment_config_id());
        Institution institution = institutionRepository.findOneByUuid(postDTO.getInstitution_id());
        //Users user = userRepository.findOneByUuid(postDTO.getUser_id());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found: ", email));

        log.info("User id :" + user.getUuid());

        Post post = new Post();
        PostDTO resDTO = new PostDTO();
        if (commentConfig != null && institution != null & user != null) {
            List<Media> medias = saveMedia(postDTO.getContent(), content);
            Text text = saveText(postDTO.getContent(), content);

            content.setText(text);
            content.setMedia(medias);

            post = postMapper.getPost(postDTO, content, commentConfig, institution, user);
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

    private ReactionCounterDTO getPostReactionCounterDTO(Post post) {

        ReactionCounterDTO reactionCounterDTO = new ReactionCounterDTO();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Users user = new Users();
        if (email != ANONYMOUS_USER) {
            user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException("User not found: ", email));
        }
        List<PostReaction> postReactions = postReactionRepository.findByPostId(post.getUuid());
        List<EmojiType> emojiTypes = emojiTypeRepository.findAll();
        //Users user = userRepository.findOneByUuid(postDTO.getUser_id());
        List<Users> postUsers =
                postReactions
                        .stream()
                        .map(postReaction -> userRepository.findOneByUuid(postReaction.getUsers().getUuid()))
                        .collect(Collectors.toList());

        List<ReactionUserDTO> reactionUsers =
                postUsers
                        .stream()
                        .map(postUser -> getReactionUserDTO(postUser, postReactions))
                        .collect(Collectors.toList());

        List<ReactionItemDTO> reactionItemDTOList = new ArrayList<>();
        Integer totalPostReactions = postReactions.size();

        for (EmojiType emojiType : emojiTypes) {
            Long reactionCounter =
                    postReactions
                            .stream()
                            .filter(postReaction -> postReaction.getEmoji_type().getEmoji_name().equals(emojiType.getEmoji_name()))
                            .count();

            ReactionItemDTO reactionItemDTO = new ReactionItemDTO();
            reactionItemDTO.setEmoji_type(emojiType.getEmoji_name());
            reactionItemDTO.setAmount(reactionCounter.intValue());

            reactionItemDTOList.add(reactionItemDTO);
        }

        String currentUserId = email != ANONYMOUS_USER ? user.getUuid() : "";

        Optional<PostReaction> optionalPostReaction = Optional.empty();
        if (currentUserId != "") {
            optionalPostReaction =
                    postReactions
                            .stream()
                            .filter(postReaction -> postReaction.getUsers().getUuid().equals(currentUserId))
                            .findFirst();
        }

        String currentUserEmojiReaction = "";
        if(optionalPostReaction.isPresent()) {
            PostReaction currentPostReaction = optionalPostReaction.get();
            currentUserEmojiReaction = currentPostReaction.getEmoji_type().getEmoji_name();
        }

        reactionCounterDTO.setMy_reaction_emoji(currentUserEmojiReaction);
        reactionCounterDTO.setTotal_reactions(totalPostReactions);
        reactionCounterDTO.setReactions_by_type(totalPostReactions != 0 ? reactionItemDTOList : new ArrayList<>());
        reactionCounterDTO.setReactions_by_user(reactionUsers);

        return reactionCounterDTO;
    }
    private ReactionUserDTO getReactionUserDTO(Users user, List<PostReaction> postReactions) {
        ReactionUserDTO reactionUserDTO = new ReactionUserDTO();

        Optional<PostReaction> postUserReaction =
                postReactions
                        .stream()
                        .filter(postReaction -> postReaction.getUsers().getUuid().equals(user.getUuid()))
                        .findFirst();

        if (postUserReaction.isPresent()) {
            reactionUserDTO.setUser_name(user.getName() + " " + user.getLastName());
            reactionUserDTO.setUser_photo(user.getPhoto_profile_path());
            reactionUserDTO.setUser_reaction(postUserReaction.get().getEmoji_type().getEmoji_name());
        }

        return reactionUserDTO;
    }

    public CommentCounterDTO getCommentCounter(String postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        int totalComments = comments.size();
        int totalReplies = comments.stream().mapToInt(comment -> comment.getReplies().size()).sum();

        CommentCounterDTO commentCounterDTO = new CommentCounterDTO();
        commentCounterDTO.setTotalComments(totalComments);
        commentCounterDTO.setTotalReplies(totalReplies);
        commentCounterDTO.setTotalCommentsAndReplies(totalComments + totalReplies);

        return commentCounterDTO;
    }

    /* Método para actualizar una publicación específica
     */
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

    /* Método para buscar publicaciones por texto  */
    public List<PostDTO> searchPosts(String text) {
        // Buscar publicaciones por texto en el repositorio
        List<Post> posts = postRepository.searchPostsByText(text);

        // Convertir las publicaciones encontradas a una lista de DTOs
        return posts.stream()
                .map(post -> postMapper.toDTO(post))
                .collect(Collectors.toList());
    }
    

}
