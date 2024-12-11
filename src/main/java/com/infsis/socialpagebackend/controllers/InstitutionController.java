package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.InstitutionDTO;
import com.infsis.socialpagebackend.services.InstitutionService;
import com.infsis.socialpagebackend.validation.ValidImageFile;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/institutions")
@Validated
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @GetMapping("/{institutionUuid}")
    public InstitutionDTO get(@PathVariable String institutionUuid) {
        return institutionService.getInstitution(institutionUuid);
    }

    @GetMapping
    public List<InstitutionDTO> getAll() {
        return institutionService.getAllInstitutions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstitutionDTO create(@Valid @RequestBody InstitutionDTO institutionDTO) {
        return institutionService.saveInstitution(institutionDTO);
    }

    @PutMapping("/{institutionUuid}")
    public InstitutionDTO update(@Valid @RequestBody InstitutionDTO institutionDTO) {
        return institutionService.updateInstitution(institutionDTO);
    }

    @DeleteMapping("/{institutionUuid}")
    public InstitutionDTO delete(@PathVariable String institutionUuid) {
        return institutionService.deleteInstitution(institutionUuid);
    }


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



}
