package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.ImageFile;
import org.springframework.stereotype.Component;

@Component
public class ImageFileMapper {

    public ImageFileDTO toDTO(ImageFile imageFile) {
        ImageFileDTO imageFileDTO = new ImageFileDTO();
        imageFileDTO.setUuid(imageFile.getUuid());
        imageFileDTO.setUrlResource(imageFile.getUrl_resource());
        imageFileDTO.setType(imageFile.getType());
        imageFileDTO.setStatus(imageFile.getStatus());

        return imageFileDTO;
    }

    public ImageFile getFile(ImageFileDTO imageFileDTO) {
        ImageFile imageFile = new ImageFile();
        imageFile.setUuid(imageFileDTO.getUuid());
        imageFile.setUrl_resource(imageFileDTO.getUrlResource());
        imageFile.setType(imageFileDTO.getType());
        imageFile.setStatus(imageFileDTO.getStatus());

        return imageFile;
    }
}
