package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.FileDTO;
import com.infsis.socialpagebackend.dtos.FileStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class ImageStorageService {

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/resources/posts/photos/";
    private static final String IMAGES_PATH = "/api/v1/images/";

    public FileDTO storeImage(MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new IOException("Image is empty");
        }

        String uniqueFileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        File uploadedImage = new File(UPLOAD_DIRECTORY + uniqueFileName);
        image.transferTo(uploadedImage);

        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(IMAGES_PATH)
                .path(uniqueFileName)
                .toUriString();

        FileDTO fileDTO = new FileDTO();
        fileDTO.setUuid(uniqueFileName);
        fileDTO.setStatus(FileStatus.SAVED_SUCCESSFULLY.name());
        fileDTO.setType(image.getContentType());
        fileDTO.setUrlResource(downloadUrl);

        return fileDTO;
    }
}