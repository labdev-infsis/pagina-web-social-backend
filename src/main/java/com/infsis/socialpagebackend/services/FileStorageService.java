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
public class FileStorageService {

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/resources/posts/documents/";
    private static final String DOCUMENTS_PATH = "/api/v1/documents/";

    public FileDTO storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }

        String uniqueFileName = UUID.randomUUID().toString();
        File uploadedFile = new File( UPLOAD_DIRECTORY + uniqueFileName);
        file.transferTo(uploadedFile);

        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DOCUMENTS_PATH)
                .path(uniqueFileName)
                .toUriString();

        FileDTO fileDTO = new FileDTO();
        fileDTO.setUuid(uniqueFileName);
        fileDTO.setStatus(FileStatus.SAVED_SUCCESSFULLY.name());
        fileDTO.setType(file.getContentType());
        fileDTO.setUrlResource(downloadUrl);

        return fileDTO;
    }
}