package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.FileItemDTO;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.FileItem;
import com.infsis.socialpagebackend.repositories.FileRepository;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
@Validated
public class ImageUploadController {

    private static final String POSTS_PHOTOS_DIRECTORY = System.getProperty("user.dir") + "/storage/institution/posts/photos/";
    private static final String INST_PROFILE_PHOTO_DIR = System.getProperty("user.dir") + "/storage/institution/profile/photos/";
    private static final String INST_COVER_DIR = System.getProperty("user.dir") + "/storage/institution/cover/photos/";
    private static final String USER_PROFILE_PHOTO_DIR = System.getProperty("user.dir") + "/storage/users/photos/";
    private static final String IMAGES_POSTS_PATH = "/api/v1/images/posts/";
    private static final String IMAGES_INSTITUTION_PROFILE_PATH = "/api/v1/images/inst-profile/";
    private static final String IMAGES_INSTITUTION_COVER_PATH = "/api/v1/images/inst-cover/";
    private static final String IMAGES_USER_PATH = "/api/v1/images/users/";
    private static final String PNG_IMAGE_TYPE = "image/png";
    private static final String JPG_IMAGE_TYPE = "image/jpg";
    private static final String JPEG_IMAGE_TYPE = "image/jpeg";

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private FileRepository fileRepository;

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FileItemDTO> handleImageUpload(@RequestParam("images") @ValidImageFile List<MultipartFile> images) throws IOException {
        return imageStorageService.storeImages(images, POSTS_PHOTOS_DIRECTORY, IMAGES_POSTS_PATH);
    }

    @PostMapping("/inst-profile")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FileItemDTO> uploadInstProfilePhoto(@RequestParam("image") @ValidImageFile MultipartFile image) throws IOException {
        List<MultipartFile> profileImage = new ArrayList<>();
        profileImage.add(image);
        return imageStorageService.storeImages(profileImage, INST_PROFILE_PHOTO_DIR, IMAGES_INSTITUTION_PROFILE_PATH);
    }

    @PostMapping("/inst-cover")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FileItemDTO> uploadInstCoverPhoto(@RequestParam("image") @ValidImageFile MultipartFile image) throws IOException {
        List<MultipartFile> coverImage = new ArrayList<>();
        coverImage.add(image);
        return imageStorageService.storeImages(coverImage, INST_COVER_DIR, IMAGES_INSTITUTION_COVER_PATH);
    }

    @PostMapping("/user-profile")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FileItemDTO> uploadUserPhoto(@RequestParam("image") @ValidImageFile MultipartFile image) throws IOException {
        List<MultipartFile> userImage = new ArrayList<>();
        userImage.add(image);
        return imageStorageService.storeImages(userImage, USER_PROFILE_PHOTO_DIR, IMAGES_USER_PATH);
    }

    @GetMapping(value = "/posts/{filename}")
    public ResponseEntity<Resource> getPostImage(@PathVariable String filename) {
        return getImage(filename, POSTS_PHOTOS_DIRECTORY);
    }

    @GetMapping(value = "/inst-profile/{filename}")
    public ResponseEntity<Resource> getInstProfImage(@PathVariable String filename) {
        return getImage(filename, INST_PROFILE_PHOTO_DIR);
    }

    @GetMapping(value = "/inst-cover/{filename}")
    public ResponseEntity<Resource> getInstCoverImage(@PathVariable String filename) {
        return getImage(filename, INST_COVER_DIR);
    }

    @GetMapping(value = "/users/{filename}")
    public ResponseEntity<Resource> getUserImage(@PathVariable String filename) {
        return getImage(filename, USER_PROFILE_PHOTO_DIR);
    }

    private ResponseEntity<Resource> getImage(String filename, String pathImages) {
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