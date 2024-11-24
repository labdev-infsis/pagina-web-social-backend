package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.SocialNetworkDTO;
import com.infsis.socialpagebackend.services.SocialNetworkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Validated
public class SocialNetworkController {

    @Autowired
    private SocialNetworkService socialNetworkService;

    @GetMapping("/institution/social-networks/{socialNetworkUuid}")
    public SocialNetworkDTO get(@PathVariable String institutionUuid, @PathVariable String socialNetworkUuid) {
        return socialNetworkService.getSocialNetwork(socialNetworkUuid);
    }

    @GetMapping("/institution/social-networks")
    public List<SocialNetworkDTO> getAll() {
        return socialNetworkService.getAllSocialNetworks();
    }

    @GetMapping("/institution/{institutionUuid}/social-networks")
    public List<SocialNetworkDTO> getAllByInstitution(@PathVariable String institutionUuid) {
        return socialNetworkService.getAllSocialNetworksByInstitution(institutionUuid);
    }

    @PostMapping("/institution/{institutionUuid}/social-networks")
    @ResponseStatus(HttpStatus.CREATED)
    public SocialNetworkDTO create(@PathVariable String institutionUuid, @Valid @RequestBody SocialNetworkDTO socialNetworkDTO) {
        return socialNetworkService.saveSocialNetwork(institutionUuid, socialNetworkDTO);
    }
}
