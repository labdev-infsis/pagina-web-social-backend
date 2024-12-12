package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.FileItemDTO;
import com.infsis.socialpagebackend.dtos.FileMapper;
import com.infsis.socialpagebackend.dtos.FileStatus;
import com.infsis.socialpagebackend.models.FileItem;
import com.infsis.socialpagebackend.repositories.FileRepository;
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
    private FileRepository fileRepository;

    @Autowired
    private FileMapper fileMapper;

    public List<FileItemDTO> storeVideos(List<MultipartFile> videos, String directory, String path) throws IOException {
        List<FileItemDTO> fileItemDTOs = new ArrayList<>();
        for (MultipartFile video : videos) {
            String uuid = UUID.randomUUID().toString();

            File uploadedFile = new File(directory + uuid);
            video.transferTo(uploadedFile);

            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(path)
                    .path(uuid)
                    .toUriString();
            FileItemDTO fileItemDTO = new FileItemDTO();
            fileItemDTO.setUuid(uuid);
            fileItemDTO.setStatus(FileStatus.SAVED_SUCCESSFULLY.name());
            fileItemDTO.setType(video.getContentType());
            fileItemDTO.setUrlResource(downloadUrl);

            FileItem fileItem = fileMapper.getFile(fileItemDTO);
            fileRepository.save(fileItem);
            fileItemDTOs.add(fileItemDTO);


        }
        return fileItemDTOs;
    }
    public ResponseEntity<Resource> getVideo(String filename, String pathVideos) {
        FileItem file = fileRepository.findOneByUuid(filename);
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


    public void deleteVideo(String uuid, String directory) {
        fileRepository.findByUuid(uuid).ifPresent(fileItem -> {
            Path filePath = Paths.get(directory, fileItem.getUuid());
            try {
                Files.deleteIfExists(filePath);
                fileRepository.delete(fileItem);
            } catch (IOException e) {
                throw new RuntimeException("Error al eliminar el video", e);
            }
        });
    }
}