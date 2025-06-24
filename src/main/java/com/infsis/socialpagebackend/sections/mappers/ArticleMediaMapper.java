package com.infsis.socialpagebackend.sections.mappers;

import com.infsis.socialpagebackend.sections.dtos.ArticleMediaDTO;
import com.infsis.socialpagebackend.sections.models.Article;
import com.infsis.socialpagebackend.sections.models.ArticleMedia;
import org.springframework.stereotype.Component;

@Component
public class ArticleMediaMapper {

    public ArticleMediaDTO toDTO(ArticleMedia media) {

        ArticleMediaDTO mediaDTO = new ArticleMediaDTO();
        mediaDTO.setUuid(media.getUuid());
        mediaDTO.setName(media.getFile_name());
        mediaDTO.setType(media.getFile_type());
        mediaDTO.setPath(media.getFile_path());
        mediaDTO.setNumber(media.getNumber());

        return mediaDTO;
    }

    public ArticleMedia getMedia(ArticleMediaDTO mediaDTO, Article article) {
        ArticleMedia media = new ArticleMedia();

        media.setArticle(article);
        media.setNumber(mediaDTO.getNumber());
        media.setFile_type(mediaDTO.getType());
        media.setFile_name(mediaDTO.getName());
        media.setFile_path(mediaDTO.getPath());
        return media;
    }
}
