package com.infsis.socialpagebackend.social_networks.controllers;

import com.infsis.socialpagebackend.social_networks.dtos.SocialNetworkDTO;
import com.infsis.socialpagebackend.social_networks.services.SocialNetworkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Validated
public class SocialNetworkController {

    @Autowired
    private SocialNetworkService socialNetworkService;

    @GetMapping("/institutions/social-networks/{socialNetworkUuid}")
    public SocialNetworkDTO get(@PathVariable String institutionUuid, @PathVariable String socialNetworkUuid) {
        return socialNetworkService.getSocialNetwork(socialNetworkUuid);
    }

    @GetMapping("/institutions/social-networks")
    public List<SocialNetworkDTO> getAll() {
        return socialNetworkService.getAllSocialNetworks();
    }

    @GetMapping("/institutions/{institutionUuid}/social-networks")
    public List<SocialNetworkDTO> getAllByInstitution(@PathVariable String institutionUuid) {
        return socialNetworkService.getAllSocialNetworksByInstitution(institutionUuid);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/institutions/{institutionUuid}/social-networks")
    @ResponseStatus(HttpStatus.CREATED)
    public List<SocialNetworkDTO> create(@PathVariable String institutionUuid, @Valid @RequestBody List<SocialNetworkDTO> socialNetworkDTO) {
        return socialNetworkService.saveSocialNetwork(institutionUuid, socialNetworkDTO);
    }
}
