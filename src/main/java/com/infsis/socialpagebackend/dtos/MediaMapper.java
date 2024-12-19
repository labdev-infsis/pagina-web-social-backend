package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.Content;
import com.infsis.socialpagebackend.models.Media;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper {

    public MediaDTO toDTO(Media media) {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setNumber(media.getNumber());
        mediaDTO.setType(media.getFile_type());
        mediaDTO.setName(media.getFile_name());
        mediaDTO.setPath(media.getFile_path());
        return mediaDTO;
    }

    public Media getMedia(MediaDTO mediaDTO, Content content) {
        Media media = new Media();

        media.setContent(content);
        media.setNumber(mediaDTO.getNumber());
        media.setFile_type(mediaDTO.getType());
        media.setFile_name(mediaDTO.getName());
        media.setFile_path(mediaDTO.getPath());
        return media;
    }
}
