package com.infsis.socialpagebackend.medias.services;

import com.infsis.socialpagebackend.configuration.ServerProperties;
import com.infsis.socialpagebackend.enums.*;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.medias.dtos.DocumentFileDTO;
import com.infsis.socialpagebackend.medias.mappers.DocumentFileMapper;
import com.infsis.socialpagebackend.medias.models.DocumentFile;
import com.infsis.socialpagebackend.medias.repositories.DocumentFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class DocumentStorageService {

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/storage/institution/posts/documents/";
    private static final String DOCUMENTS_PATH = "/api/v1/documents/";
    private static final String SECURE_PORT = "443";

    @Autowired
    private DocumentFileRepository documentFileRepository;

    @Autowired
    private DocumentFileMapper documentFileMapper;

    @Autowired
    private ServerProperties serverProperties;

    public DocumentFileDTO getDocument(String documentUuid) {
        DocumentFile documentFile = documentFileRepository.findOneByUuid(documentUuid);
        if(documentFile == null) {
            throw new NotFoundException("Document", documentUuid);
        }

        return documentFileMapper.toDTO(documentFile);

    }

    public DocumentFileDTO storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("ImageFile is empty");
        }

        String uniqueFileName = UUID.randomUUID().toString();
        //String fileName = file.getOriginalFilename().replaceAll("\\s+", "_");
        File uploadedFile = new File( UPLOAD_DIRECTORY + uniqueFileName);
        file.transferTo(uploadedFile);

        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DOCUMENTS_PATH)
                .path(uniqueFileName)
                .toUriString();

        DocumentFileDTO documentFileDTO = new DocumentFileDTO();
        documentFileDTO.setUuid(uniqueFileName);
        documentFileDTO.setName(file.getOriginalFilename());
        documentFileDTO.setStatus(FileStatus.SAVED_SUCCESSFULLY.name());
        documentFileDTO.setType(file.getContentType());
        documentFileDTO.setUrlResource(downloadUrl);

        documentFileRepository.save(documentFileMapper.getFile(documentFileDTO));

        return documentFileDTO;
    }

    public void deleteDocument(String documentUuid,String directory) {
        DocumentFile documentFile = documentFileRepository.findOneByUuid(documentUuid);
        Path filePath = Paths.get(directory, documentFile.getUuid());
        try {
            Files.deleteIfExists(filePath); // Eliminar el archivo del sistema de archivos
            documentFileRepository.delete(documentFile);// Eliminar el registro de la base de datos
        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar el doc", e);
        }
    }
}