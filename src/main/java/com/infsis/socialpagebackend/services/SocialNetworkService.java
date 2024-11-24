package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.SocialNetworkDTO;
import com.infsis.socialpagebackend.dtos.SocialNetworkMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.Institution;
import com.infsis.socialpagebackend.models.SocialNetwork;
import com.infsis.socialpagebackend.repositories.InstitutionRepository;
import com.infsis.socialpagebackend.repositories.SocialNetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public SocialNetworkDTO saveSocialNetwork(String institutionUuid, SocialNetworkDTO socialNetworkDTO) {

        Institution institution = institutionRepository.findOneByUuid(institutionUuid);
        SocialNetwork socialNetwork = socialNetworkMapper.getSocialNetwork(socialNetworkDTO, institution);
        socialNetworkRepository.save(socialNetwork);

        return socialNetworkMapper.toDTO(socialNetwork);
    }
}
