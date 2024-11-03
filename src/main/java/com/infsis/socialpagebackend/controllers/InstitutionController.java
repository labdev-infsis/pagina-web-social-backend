package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.InstitutionDTO;
import com.infsis.socialpagebackend.services.InstitutionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institution")
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

}
