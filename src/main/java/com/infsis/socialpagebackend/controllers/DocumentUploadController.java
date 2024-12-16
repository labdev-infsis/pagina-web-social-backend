package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.DocumentFileDTO;
import com.infsis.socialpagebackend.services.DocumentStorageService;
import com.infsis.socialpagebackend.validation.ValidDocumentFile;
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

@RestController
@RequestMapping("/api/v1/documents")
@Validated
public class DocumentUploadController {

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/storage/institution/posts/documents/";

    @Autowired
    private DocumentStorageService documentStorageService;

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentFileDTO handleFileUpload(@RequestParam("file") @ValidDocumentFile MultipartFile file) throws IOException {
            return documentStorageService.storeFile(file);
    }

    @GetMapping("/{documentUuid}/info")
    public DocumentFileDTO getDocumentInfo(@PathVariable String documentUuid) {
        return documentStorageService.getDocument(documentUuid);
    }

    @GetMapping("/{documentUuid}")
    public ResponseEntity<Resource> getResourceDocument(@PathVariable String documentUuid) {
        try {
            Path filePath = Paths.get(UPLOAD_DIRECTORY).resolve(documentUuid);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}