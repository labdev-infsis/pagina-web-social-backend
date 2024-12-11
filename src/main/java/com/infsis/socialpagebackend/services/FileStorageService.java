package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.FileItemDTO;
import com.infsis.socialpagebackend.dtos.FileMapper;
import com.infsis.socialpagebackend.dtos.FileStatus;
import com.infsis.socialpagebackend.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStorageService {

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/storage/institution/posts/documents/";
    private static final String DOCUMENTS_PATH = "/api/v1/documents/";

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileMapper fileMapper;

    public FileItemDTO storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("FileItem is empty");
        }

        String uniqueFileName = UUID.randomUUID().toString();
        File uploadedFile = new File( UPLOAD_DIRECTORY + uniqueFileName);
        file.transferTo(uploadedFile);

        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DOCUMENTS_PATH)
                .path(uniqueFileName)
                .toUriString();

        FileItemDTO fileItemDTO = new FileItemDTO();
        fileItemDTO.setUuid(uniqueFileName);
        fileItemDTO.setStatus(FileStatus.SAVED_SUCCESSFULLY.name());
        fileItemDTO.setType(file.getContentType());
        fileItemDTO.setUrlResource(downloadUrl);

        fileRepository.save(fileMapper.getFile(fileItemDTO));

        return fileItemDTO;
    }
}