package com.infsis.socialpagebackend.sections.mappers;

import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.comments.dtos.CommentCounterDTO;
import com.infsis.socialpagebackend.institutions.models.Institution;
import com.infsis.socialpagebackend.posts.dtos.PostDTO;
import com.infsis.socialpagebackend.posts.dtos.ReactionCounterDTO;
import com.infsis.socialpagebackend.posts.models.CommentConfig;
import com.infsis.socialpagebackend.posts.models.Content;
import com.infsis.socialpagebackend.posts.models.Post;
import com.infsis.socialpagebackend.sections.dtos.ArticleDTO;
import com.infsis.socialpagebackend.sections.dtos.SectionDTO;
import com.infsis.socialpagebackend.sections.models.Article;
import com.infsis.socialpagebackend.sections.models.ArticleMedia;
import com.infsis.socialpagebackend.sections.models.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {

    @Autowired
    private ArticleMediaMapper articleMediaMapper;

    public ArticleDTO toDTO(Article article) {

        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setUuid(article.getUuid());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setText(article.getText());
        articleDTO.setDate(article.getDate());
        articleDTO.setUser_id(article.getUsers().getUuid());
        articleDTO.setSection_id(article.getSection().getUuid());
        articleDTO.setMedias(
                        article.getArticle_medias()
                                .stream()
                                .map(media -> articleMediaMapper.toDTO(media))
                                .collect(Collectors.toList())
        );

        return articleDTO;
    }

    public Article getArticle(ArticleDTO articleDTO, Section section, Users user) {

        Article article = new Article();

        article.setUsers(user);
        article.setSection(section);
        article.setTitle(articleDTO.getTitle());
        article.setText(articleDTO.getText());
        article.setDate(articleDTO.getDate());

        return article;
    }

    public Article getArticle(ArticleDTO articleDTO, List<ArticleMedia> medias, Section section, Users user) {

        Article article = new Article();

        article.setUsers(user);
        article.setSection(section);
        article.setTitle(articleDTO.getTitle());
        article.setText(articleDTO.getText());
        article.setDate(articleDTO.getDate());
        article.setArticle_medias(medias);

        return article;
    }

}
