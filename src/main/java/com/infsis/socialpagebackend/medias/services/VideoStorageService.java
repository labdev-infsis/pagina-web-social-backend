package com.infsis.socialpagebackend.medias.services.services;

import com.infsis.socialpagebackend.enums.*;
import com.infsis.socialpagebackend.medias.dtos.VideoFileDTO;
import com.infsis.socialpagebackend.medias.mappers.VideoFileMapper;
import com.infsis.socialpagebackend.medias.models.VideoFile;
import com.infsis.socialpagebackend.medias.repositories.VideoFileRepository;
import com.infsis.socialpagebackend.posts.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VideoStorageService {

    @Autowired
    private VideoFileRepository videoFileRepository;

    @Autowired
    private VideoFileMapper videoFileMapper;

    @Autowired
    private MediaRepository mediaRepository;

    public List<VideoFileDTO> storeVideos(List<MultipartFile> videos, String directory, String path) throws IOException {
        List<VideoFileDTO> videoFileDTOS = new ArrayList<>();
        for (MultipartFile video : videos) {
            String uuid = UUID.randomUUID().toString();

            File uploadedFile = new File(directory + uuid);
            video.transferTo(uploadedFile);

            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(path)
                    .path(uuid)
                    .toUriString();
            VideoFileDTO videoFileDTO = new VideoFileDTO();
            videoFileDTO.setUuid(uuid);
            videoFileDTO.setName(video.getOriginalFilename());
            videoFileDTO.setStatus(FileStatus.SAVED_SUCCESSFULLY.name());
            videoFileDTO.setType(video.getContentType());
            videoFileDTO.setUrlResource(downloadUrl);

            VideoFile videoFile = videoFileMapper.getFile(videoFileDTO);
            videoFileRepository.save(videoFile);
            videoFileDTOS.add(videoFileDTO);


        }
        return videoFileDTOS;
    }
    public ResponseEntity<Resource> getVideo(String filename, String pathVideos) {
        VideoFile file = videoFileRepository.findOneByUuid(filename);
        if (file == null) {
            throw new NotFoundException("File:", filename);
        }
        MediaType videoContentType = MediaType.APPLICATION_OCTET_STREAM;
        if (file.getType().equals("video/mp4")) {
            videoContentType = MediaType.valueOf("video/mp4");
        } else if (file.getType().equals("video/mpeg")) {
            videoContentType = MediaType.valueOf("video/mpeg");
        }

        try {
            Path filePath = Paths.get(pathVideos).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(videoContentType)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**Eliminar un video por su  UUID*/
    public void deleteVideo(String uuid, String directory) {
        videoFileRepository.findByUuid(uuid).ifPresent(fileItem -> {
            Path filePath = Paths.get(directory, fileItem.getUuid());
            String urlResource = fileItem.getUrl_resource();
            try {
                Files.deleteIfExists(filePath); // Eliminar el archivo del sistema de archivos
                videoFileRepository.delete(fileItem); // Eliminar el registro de la base de datos
                mediaRepository.deleteByFilePath(urlResource);//Eliminar el registro de la tabla media
            } catch (IOException e) {
                throw new RuntimeException("Error al eliminar el video", e);
            }
        });
    }
}