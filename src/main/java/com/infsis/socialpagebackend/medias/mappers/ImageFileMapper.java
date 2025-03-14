package com.infsis.socialpagebackend.medias.mappers;

import com.infsis.socialpagebackend.medias.dtos.ImageFileDTO;
import com.infsis.socialpagebackend.medias.models.ImageFile;
import org.springframework.stereotype.Component;

@Component
public class ImageFileMapper {

    public ImageFileDTO toDTO(ImageFile imageFile) {
        ImageFileDTO imageFileDTO = new ImageFileDTO();
        imageFileDTO.setUuid(imageFile.getUuid());
        imageFileDTO.setName(imageFile.getName());
        imageFileDTO.setUrlResource(imageFile.getUrl_resource());
        imageFileDTO.setType(imageFile.getType());
        imageFileDTO.setStatus(imageFile.getStatus());

        return imageFileDTO;
    }

    public ImageFile getFile(ImageFileDTO imageFileDTO) {
        ImageFile imageFile = new ImageFile();
        imageFile.setUuid(imageFileDTO.getUuid());
        imageFile.setName(imageFile.getName());
        imageFile.setUrl_resource(imageFileDTO.getUrlResource());
        imageFile.setType(imageFileDTO.getType());
        imageFile.setStatus(imageFileDTO.getStatus());

        return imageFile;
    }
}
