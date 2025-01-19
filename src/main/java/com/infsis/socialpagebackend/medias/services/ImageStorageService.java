package com.infsis.socialpagebackend.medias.services;

import com.infsis.socialpagebackend.configuration.ServerProperties;
import com.infsis.socialpagebackend.medias.dtos.ImageFileDTO;
import com.infsis.socialpagebackend.medias.mappers.ImageFileMapper;
import com.infsis.socialpagebackend.enums.FileStatus;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.medias.models.ImageFile;
import com.infsis.socialpagebackend.medias.repositories.ImageFileRepository;
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
    private ImageFileRepository imageFileRepository;

    @Autowired
    private ImageFileMapper imageFileMapper;

    @Autowired
    private ServerProperties serverProperties;

    public List<ImageFileDTO> storeImages(List<MultipartFile> images, String directory, String imagesPath) throws IOException {
        List<ImageFileDTO> imageFileDTOList = new ArrayList<>();

        for (MultipartFile image : images) {
            if (image.isEmpty()) {
                throw new IOException("ImageFile is empty");
            }

            String uniqueFileName = UUID.randomUUID().toString();

            File uploadedFile = new File(directory + uniqueFileName);
            image.transferTo(uploadedFile);

            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(imagesPath)
                    .path(uniqueFileName)
                    .toUriString();

            ImageFile imageFile = new ImageFile();
            imageFile.setUuid(uniqueFileName);
            imageFile.setName(image.getOriginalFilename());
            imageFile.setStatus(FileStatus.SAVED_SUCCESSFULLY.name());
            imageFile.setType(image.getContentType());
            imageFile.setUrl_resource(downloadUrl);

            imageFileRepository.save(imageFile);
            imageFileDTOList.add(imageFileMapper.toDTO(imageFile));
        }

        return imageFileDTOList;
    }

    public ResponseEntity<Resource> getResourceImage(String filename, String pathImages) {
        ImageFile file = imageFileRepository.findOneByUuid(filename);
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