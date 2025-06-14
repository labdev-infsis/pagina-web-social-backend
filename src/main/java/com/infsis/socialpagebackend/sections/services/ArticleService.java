package com.infsis.socialpagebackend.sections.services;

import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.authentication.repositories.UserRepository;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.sections.dtos.ArticleDTO;
import com.infsis.socialpagebackend.sections.dtos.ArticleMediaDTO;
import com.infsis.socialpagebackend.sections.mappers.ArticleMapper;
import com.infsis.socialpagebackend.sections.mappers.ArticleMediaMapper;
import com.infsis.socialpagebackend.sections.models.Article;
import com.infsis.socialpagebackend.sections.models.ArticleMedia;
import com.infsis.socialpagebackend.sections.models.Section;
import com.infsis.socialpagebackend.sections.repositories.ArticleMediaRepository;
import com.infsis.socialpagebackend.sections.repositories.ArticleRepository;
import com.infsis.socialpagebackend.sections.repositories.SectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleMediaMapper mediaMapper;

    @Autowired
    private ArticleMediaRepository articleMediaRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private UserRepository userRepository;

    public ArticleDTO getArticle(String articleUuid) {
        Article article = articleRepository.findOneByUuid(articleUuid);

        if(article == null || article.isDeleted()) {
            throw new NotFoundException("Article", articleUuid);
        }

        return articleMapper.toDTO(article);
    }

    public List<ArticleDTO> getAllArticles() {
        return articleRepository
                .findAll()
                .stream()
                .filter(article -> !article.isDeleted())
                .map(article -> articleMapper.toDTO(article))
                .collect(Collectors.toList());
    }

    public List<ArticleDTO> getAllBySection(String sectionUuid) {
        return articleRepository
                .findAll()
                .stream()
                .filter(article -> article.getSection().getUuid().equals(sectionUuid) && !article.isDeleted())
                .map(article -> articleMapper.toDTO(article))
                .collect(Collectors.toList());
    }

    public ArticleDTO saveArticle(ArticleDTO articleDTO) {

        Section section = sectionRepository.findOneByUuid(articleDTO.getSection_id());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found: ", email));

        log.info("User id :" + user.getUuid());

        ArticleDTO resDTO = new ArticleDTO();
        Article article = articleRepository.save(new Article());
        if (section != null & user != null) {
            List<ArticleMedia> medias = saveMedia(articleDTO.getMedias(), article);

            article.setUsers(user);
            article.setSection(section);
            article.setTitle(articleDTO.getTitle());
            article.setText(articleDTO.getText());
            article.setDate(articleDTO.getDate());
            article.setArticle_medias(medias);

            articleRepository.save(article);

            resDTO = articleMapper.toDTO(article);
        }

        return resDTO;
    }

    public ArticleDTO deleteArticle(String articleUuid) {
        Article article = articleRepository.findOneByUuid(articleUuid);
        article.setDeleted(true);
        articleRepository.save(article);
        return articleMapper.toDTO(article);
    }

    private List<ArticleMedia> saveMedia(List<ArticleMediaDTO> mediaDTOS, Article article){
        List<ArticleMedia> medias = new ArrayList<>();
        if(mediaDTOS != null ) {
            medias = mediaDTOS
                    .stream()
                    .map(media -> mediaMapper.getMedia(media, article))
                    .collect(Collectors.toList());

            medias.stream().forEach(
                    (media) -> articleMediaRepository.save(media)
            );
        }
        return medias;
    }

    public ArticleDTO updateArticle(String articleUuid, ArticleDTO articleDTO) {
        Article foundArticle = articleRepository.findOneByUuid(articleUuid);

        if (foundArticle == null || foundArticle.isDeleted()) {
            throw new NotFoundException("Article", articleUuid);
        }

        if (articleDTO.getMedias() != null) {
            List<ArticleMedia> medias = saveMedia(articleDTO.getMedias(), foundArticle);
            foundArticle.setArticle_medias(medias);
        }

        if (articleDTO.getSection_id() != null) {
            Section section = sectionRepository.findOneByUuid(articleDTO.getSection_id());
            foundArticle.setSection(section);
        }

        foundArticle.setDate(articleDTO.getDate());
        foundArticle.setTitle(articleDTO.getTitle());
        foundArticle.setText(articleDTO.getText());

        Article updatedArticle = articleRepository.save(foundArticle);

        return articleMapper.toDTO(updatedArticle);
    }
}
