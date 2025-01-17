package com.infsis.socialpagebackend.social_networks.services;

import com.infsis.socialpagebackend.social_networks.dtos.SocialNetworkDTO;
import com.infsis.socialpagebackend.social_networks.mappers.SocialNetworkMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.institutions.models.Institution;
import com.infsis.socialpagebackend.social_networks.models.SocialNetwork;
import com.infsis.socialpagebackend.institutions.repositories.InstitutionRepository;
import com.infsis.socialpagebackend.social_networks.repositories.SocialNetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SocialNetworkService {

    @Autowired
    private SocialNetworkRepository socialNetworkRepository;

    @Autowired
    private SocialNetworkMapper socialNetworkMapper;

    @Autowired
    private InstitutionRepository institutionRepository;

    public SocialNetworkDTO getSocialNetwork(String socialNetworkUuid) {
        SocialNetwork socialNetwork = socialNetworkRepository.findOneByUuid(socialNetworkUuid);
        if(socialNetwork == null) {
            throw new NotFoundException("SocialNetwork", socialNetworkUuid);
        }
        return socialNetworkMapper.toDTO(socialNetwork);
    }

    public List<SocialNetworkDTO> getAllSocialNetworks() {
        return socialNetworkRepository
                .findAll()
                .stream()
                .map(socialNetwork -> socialNetworkMapper.toDTO(socialNetwork))
                .collect(Collectors.toList());
    }

    public List<SocialNetworkDTO> getAllSocialNetworksByInstitution(String institutionUuid) {
        return socialNetworkRepository
                .findByInstitutionUuid(institutionUuid)
                .stream()
                .map(socialNetwork -> socialNetworkMapper.toDTO(socialNetwork))
                .collect(Collectors.toList());
    }

    public List<SocialNetworkDTO> saveSocialNetwork(String institutionUuid, List<SocialNetworkDTO> socialNetworkDTOs) {

        Institution institution = institutionRepository.findOneByUuid(institutionUuid);
        List<SocialNetworkDTO> resSocialNetworksDTOs = new ArrayList<>();
        for (SocialNetworkDTO socialNetworkDTO : socialNetworkDTOs) {
            SocialNetwork socialNetwork = socialNetworkMapper.getSocialNetwork(socialNetworkDTO, institution);
            socialNetworkRepository.save(socialNetwork);

            resSocialNetworksDTOs.add(socialNetworkMapper.toDTO(socialNetwork));
        }

        return resSocialNetworksDTOs;
    }
}
