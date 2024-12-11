package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.FileItem;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public FileItemDTO toDTO(FileItem fileItem) {
        FileItemDTO fileItemDTO = new FileItemDTO();
        fileItemDTO.setUuid(fileItem.getUuid());
        fileItemDTO.setUrlResource(fileItem.getUrl_resource());
        fileItemDTO.setType(fileItem.getType());
        fileItemDTO.setStatus(fileItem.getStatus());

        return fileItemDTO;
    }

    public FileItem getFile(FileItemDTO fileItemDTO) {
        FileItem fileItem = new FileItem();
        fileItem.setUuid(fileItemDTO.getUuid());
        fileItem.setUrl_resource(fileItemDTO.getUrlResource());
        fileItem.setType(fileItemDTO.getType());
        fileItem.setStatus(fileItemDTO.getStatus());

        return fileItem;
    }
}
