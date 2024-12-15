package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.configuration.ServerProperties;
import com.infsis.socialpagebackend.dtos.FileItemDTO;
import com.infsis.socialpagebackend.dtos.FileMapper;
import com.infsis.socialpagebackend.dtos.FileStatus;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.FileItem;
import com.infsis.socialpagebackend.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ImageStorageService {

    private static final String SECURE_PORT = "443";
    private static final String PNG_IMAGE_TYPE = "image/png";
    private static final String JPG_IMAGE_TYPE = "image/jpg";
    private static final String JPEG_IMAGE_TYPE = "image/jpeg";

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private ServerProperties serverProperties;

    public List<FileItemDTO> storeImages(List<MultipartFile> images, String directory, String imagesPath) throws IOException {
        List<FileItemDTO> fileItemDTOList = new ArrayList<>();

        for (MultipartFile image : images) {
            if (image.isEmpty()) {
                throw new IOException("FileItem is empty");
            }

            String uniqueFileName = UUID.randomUUID().toString();

            File uploadedFile = new File(directory + uniqueFileName);
            image.transferTo(uploadedFile);

            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .scheme(serverProperties.getSchema())
                    .host(serverProperties.getHost())
                    .port(serverProperties.getPort().equals(SECURE_PORT) ? "" : serverProperties.getPort())
                    .path(imagesPath)
                    .path(uniqueFileName)
                    .toUriString();

            FileItemDTO fileItemDTO = new FileItemDTO();
            fileItemDTO.setUuid(uniqueFileName);
            fileItemDTO.setStatus(FileStatus.SAVED_SUCCESSFULLY.name());
            fileItemDTO.setType(image.getContentType());
            fileItemDTO.setUrlResource(downloadUrl);

            FileItem fileItem = fileMapper.getFile(fileItemDTO);
            fileRepository.save(fileItem);
            fileItemDTOList.add(fileItemDTO);
        }

        return fileItemDTOList;
    }

    public ResponseEntity<Resource> getImage(String filename, String pathImages) {
        FileItem file = fileRepository.findOneByUuid(filename);
        if(file == null) {
            throw new NotFoundException("File:", filename);
        }
        MediaType imageContentType = new MediaType(MediaType.IMAGE_JPEG);
        if (file.getType().equals(PNG_IMAGE_TYPE)) {
            imageContentType = MediaType.IMAGE_PNG;
        } else if (file.getType().equals(JPEG_IMAGE_TYPE) || file.getType().equals(JPG_IMAGE_TYPE)) {
            imageContentType = MediaType.IMAGE_JPEG;
        }

        try {
            Path filePath = Paths.get(pathImages).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(imageContentType)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}