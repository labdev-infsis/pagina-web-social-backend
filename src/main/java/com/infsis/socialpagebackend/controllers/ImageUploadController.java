package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.FileDTO;
import com.infsis.socialpagebackend.services.ImageStorageService;
import com.infsis.socialpagebackend.validation.ValidImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
@Validated
public class ImageUploadController {

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/resources/posts/photos/";

    @Autowired
    private ImageStorageService imageStorageService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FileDTO> handleImageUpload(@RequestParam("images") @ValidImageFile List<MultipartFile> images) throws IOException {
        return imageStorageService.storeImages(images);
    }

    @GetMapping(value = "/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(UPLOAD_DIRECTORY).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}