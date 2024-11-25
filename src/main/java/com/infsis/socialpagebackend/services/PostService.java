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

    public PostDTO getPost(String postUuid) {
        Post post = postRepository.findOneByUuid(postUuid);

        if(post == null) {
            throw new NotFoundException("Post", postUuid);
        }
        ReactionCounterDTO reactionCounterDTO = getPostReactionCounterDTO(post);

        return postMapper.toDTO(post, reactionCounterDTO);
    }

    public List<PostDTO> getAllPost() {
        return postRepository
                .findAll()
                .stream()
                .map(post -> postMapper.toDTO(post, getPostReactionCounterDTO(post)))
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
        reactionCounterDTO.setReactions(totalPostReactions != 0 ? reactionItemDTOList : null);

        return reactionCounterDTO;
    }

}
