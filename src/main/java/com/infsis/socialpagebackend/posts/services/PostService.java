package com.infsis.socialpagebackend.posts.services;

import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.authentication.repositories.UserRepository;
import com.infsis.socialpagebackend.comments.dtos.CommentCounterDTO;
import com.infsis.socialpagebackend.comments.models.Comment;
import com.infsis.socialpagebackend.comments.repositories.CommentRepository;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.institutions.models.Institution;
import com.infsis.socialpagebackend.institutions.repositories.InstitutionRepository;
import com.infsis.socialpagebackend.posts.dtos.*;
import com.infsis.socialpagebackend.posts.mappers.MediaMapper;
import com.infsis.socialpagebackend.posts.mappers.PostMapper;
import com.infsis.socialpagebackend.posts.models.*;
import com.infsis.socialpagebackend.posts.repositories.*;
import com.infsis.socialpagebackend.reactions.models.EmojiType;
import com.infsis.socialpagebackend.reactions.models.PostReaction;
import com.infsis.socialpagebackend.reactions.repositories.EmojiTypeRepository;
import com.infsis.socialpagebackend.reactions.repositories.PostReactionRepository;
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

    public enum GroupStatus {
        CREATED, SAVED, REMOVED, UPDATED
    }

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

    @Autowired
    private GroupRepository groupRepository;

    public PostDTO getPost(String postUuid) {
        Post post = postRepository.findOneByUuid(postUuid);

        if(post == null || post.isDeleted()) {
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
                .filter(post -> !post.isDeleted())
                .map(post -> postMapper.toDTO(post, getPostReactionCounterDTO(post), getCommentCounter(post.getUuid())))
                .collect(Collectors.toList());
    }

    public List<PostDTO> getAllByGroup(String groupUuid) {

        return postRepository
                .findAll()
                .stream()
                .filter(post -> isFromGroup(groupUuid, post) && !post.isDeleted())
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

    public PostDTO updateePost(String postUuid, PostDTO postDTO) {

        Post post = postRepository.findOneByUuid(postUuid);

        if(post == null || post.isDeleted()) {
            throw new NotFoundException("Post", postUuid);
        }

        return savePost(postDTO);

    }

    public PostDTO deletePost(String postUuid) {
        Post post = postRepository.findOneByUuid(postUuid);
        post.setDeleted(true);
        postRepository.save(post);
        return postMapper.toDTO(post);
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
    public PostDTO updatePost(String postUuid, PostDTO postDTO) {
        // Buscamos la publicación en la base de datos utilizando su UUID
        Post existingPost = postRepository.findOneByUuid(postUuid);
        if (existingPost == null || existingPost.isDeleted()) { // Si no existe, lanzamos una excepción personalizada
            throw new NotFoundException("Post", postUuid);
        }


        // Si el contenido está presente en el DTO, actualizamos el contenido asociado
        if (postDTO.getContent() != null) {
            Content content = contentRepository.save(new Content());

            List<Media> medias = saveMedia(postDTO.getContent(), content);
            Text text = saveText(postDTO.getContent(), content);

            content.setText(text);
            content.setMedia(medias);

            content.setPost(existingPost);
            contentRepository.save(content);

            existingPost.setContent(content); // Asociamos el contenido actualizado a la publicación
        }

        // Actualizamos la configuración de comentarios si se envía un nuevo valor
        if (postDTO.getComment_config_id() != null) {
            CommentConfig commentConfig = commentConfigRepository.findOneByUuid(postDTO.getComment_config_id());
            existingPost.setComment_conf(commentConfig);
        }

        Institution institution = institutionRepository.findOneByUuid(postDTO.getInstitution_id());

        existingPost.setPost_date(postDTO.getDate());
        existingPost.setInstitution(institution);

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

    public PostGroupDTO addToGroup(String postUuid, PostGroupDTO postGroupDTO) {

        Post currentPost = postRepository.findOneByUuid(postUuid);
        Group currentGroup = groupRepository.findOneByUuid(postGroupDTO.getGroup_uuid());

        if(!isFromGroup(postGroupDTO.getGroup_uuid(), currentPost)) {
            List<Group> groups;
            groups = currentPost.getGroups();
            groups.add(currentGroup);
            currentPost.setGroups(groups);

            postRepository.save(currentPost);
        }

        postGroupDTO.setPost_uuid(postUuid);
        postGroupDTO.setStatus(GroupStatus.SAVED.name());

        return postGroupDTO;
    }

    public PostGroupDTO removeFromGroup(String postUuid, PostGroupDTO postGroupDTO) {

        Post currentPost = postRepository.findOneByUuid(postUuid);
        Group currentGroup = groupRepository.findOneByUuid(postGroupDTO.getGroup_uuid());

        List<Group> groups;
        groups = currentPost.getGroups();
        groups.remove(currentGroup);
        currentPost.setGroups(groups);

        postRepository.save(currentPost);

        postGroupDTO.setPost_uuid(postUuid);
        postGroupDTO.setStatus(GroupStatus.REMOVED.name());

        return postGroupDTO;
    }

    private boolean isFromGroup(String groupUuid, Post post){

        return !post.getGroups()
                .stream()
                .filter(group -> group.getUuid().equals(groupUuid))
                .collect(Collectors.toList())
                .isEmpty();
    }

    public List<MediaItemDTO> getMediasInstitution(String institutionUuid, String type) {
        List<Post> posts = postRepository.findAll();
        List<MediaItemDTO> mediaItems = new ArrayList<>();
        for (Post post : posts) {
            if (post.getInstitution().getUuid().equals(institutionUuid)) {
                for (Media media : post.getContent().getMedia()) {
                    if (type.equalsIgnoreCase(media.getFile_type())) {
                        MediaItemDTO mediaItemDTO = new MediaItemDTO();
                        mediaItemDTO.setUuid_post(post.getUuid());
                        mediaItemDTO.setPath(media.getFile_path());
                        mediaItems.add(mediaItemDTO);
                    }
                }
            }
        }
        return mediaItems;
    }
}
