package com.infsis.socialpagebackend.sections.controllers;

import com.infsis.socialpagebackend.sections.dtos.SectionDTO;
import com.infsis.socialpagebackend.sections.services.SectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sections")
@Validated
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping("/{sectionUuid}")
    public SectionDTO get(@PathVariable String sectionUuid) {
        return sectionService.getSection(sectionUuid);
    }

    @GetMapping
    public List<SectionDTO> getAll() {
        return sectionService.getAllSections();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SectionDTO create(@Valid @RequestBody SectionDTO sectionDTO) {
        return sectionService.saveSection(sectionDTO);
    }

    @DeleteMapping("/{sectionUuid}")
    @ResponseStatus(HttpStatus.OK)
    public SectionDTO delete(@PathVariable String sectionUuid) {
        return sectionService.deleteSection(sectionUuid);
    }

    @PutMapping("/{sectionUuid}")
    @ResponseStatus(HttpStatus.OK)
    public SectionDTO update(@PathVariable String sectionUuid, @Valid @RequestBody SectionDTO sectionDTO) {
        return sectionService.updateSection(sectionUuid, sectionDTO);
    }
}
