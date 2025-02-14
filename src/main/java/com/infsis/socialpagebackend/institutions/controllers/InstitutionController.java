package com.infsis.socialpagebackend.institutions.controllers;

import com.infsis.socialpagebackend.posts.services.PostService;
import com.infsis.socialpagebackend.posts.dtos.MediaItemDTO;
import com.infsis.socialpagebackend.institutions.dtos.InstitutionDTO;
import com.infsis.socialpagebackend.institutions.services.InstitutionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/institutions")
@Validated
public class InstitutionController {
    public static final String IMAGE = "image";
    public static final String VIDEO = "video";
    public static final String DOCUMENT   = "document";
    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private PostService postService;

    @GetMapping("/{institutionUuid}")
    public InstitutionDTO get(@PathVariable String institutionUuid) {
        return institutionService.getInstitution(institutionUuid);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<InstitutionDTO> getAll() {
        return institutionService.getAllInstitutions();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstitutionDTO create(@Valid @RequestBody InstitutionDTO institutionDTO) {
        return institutionService.saveInstitution(institutionDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{institutionUuid}")
    public InstitutionDTO update(@Valid @RequestBody InstitutionDTO institutionDTO) {
        return institutionService.updateInstitution(institutionDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{institutionUuid}")
    public InstitutionDTO delete(@PathVariable String institutionUuid) {
        return institutionService.deleteInstitution(institutionUuid);
    }

/*
    @PostMapping("/{institutionUuid}/profile-photo")
    @ResponseStatus(HttpStatus.OK)
    public InstitutionDTO saveProfilePhoto(@PathVariable String institutionUuid, @RequestParam("image") @ValidImageFile MultipartFile photo) throws IOException {
        List<MultipartFile> institutionProfileImage = new ArrayList<>();
        institutionProfileImage.add(photo);

        return institutionService.saveProfileImage(institutionUuid, institutionProfileImage);
    }

    @PostMapping("/{institutionUuid}/cover-photo")
    @ResponseStatus(HttpStatus.OK)
    public InstitutionDTO saveCoverPhoto(@PathVariable String institutionUuid, @RequestParam("image") @ValidImageFile MultipartFile photo) throws IOException {
        List<MultipartFile> institutionCoverImage = new ArrayList<>();
        institutionCoverImage.add(photo);

        return institutionService.saveCoverImage(institutionUuid, institutionCoverImage);
    }
*/
    @GetMapping("/{institutionUuid}/photos")
    public List<MediaItemDTO> getPhotosByInstitution(@PathVariable String institutionUuid) {
        return postService.getMediasInstitution(institutionUuid, IMAGE);
    }

    @GetMapping("/{institutionUuid}/videos")
    public List<MediaItemDTO> getVideosByInstitution(@PathVariable String institutionUuid) {
        return postService.getMediasInstitution(institutionUuid, VIDEO);
    }

    @GetMapping("/{institutionUuid}/documents")
    public List<MediaItemDTO> getDocsByInstitution(@PathVariable String institutionUuid) {
        return postService.getMediasInstitution(institutionUuid, DOCUMENT);
    }
}
