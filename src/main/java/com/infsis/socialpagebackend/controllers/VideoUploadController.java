package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.FileItemDTO;
import com.infsis.socialpagebackend.services.VideoStorageService;
import com.infsis.socialpagebackend.validation.ValidVideoFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/videos")
@Validated
public class VideoUploadController {

    private static final String VIDEOS_DIRECTORY = System.getProperty("user.dir") + "/storage/institution/posts/videos/";
    private static final String VIDEOS_PATH = "/api/v1/videos/posts/";

    @Autowired
    private VideoStorageService videoStorageService;

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public List<FileItemDTO> handleVideoUpload(@RequestParam("videos") @ValidVideoFile List<MultipartFile> videos) throws IOException {
        return videoStorageService.storeVideos(videos, VIDEOS_DIRECTORY, VIDEOS_PATH);
    }
    @GetMapping(value = "/posts/{filename}")
    public ResponseEntity<Resource> getPostVideo(@PathVariable String filename) {
        return videoStorageService.getVideo(filename, VIDEOS_DIRECTORY);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteVideo(@PathVariable String uuid) {
        videoStorageService.deleteVideo(uuid, VIDEOS_DIRECTORY);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Video eliminado con éxito");
    }
}