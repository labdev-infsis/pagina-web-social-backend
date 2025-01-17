package com.infsis.socialpagebackend.posts.mappers;

import com.infsis.socialpagebackend.posts.models.Content;
import com.infsis.socialpagebackend.posts.dtos.ContentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ContentMapper {

    @Autowired
    private MediaMapper mediaMapper;

    public ContentDTO toDTO(Content content) {
        ContentDTO contentDTO = new ContentDTO();
        contentDTO.setText(content.getText() != null ? content.getText().getText() : "");
        contentDTO.setMedia(
                content.getMedia()
                        .stream()
                        .map(media -> mediaMapper.toDTO(media))
                        .collect(Collectors.toList())
        );
        return contentDTO;

    }
}
