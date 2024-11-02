package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.InstitutionDTO;
import com.infsis.socialpagebackend.dtos.InstitutionMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.Institution;
import com.infsis.socialpagebackend.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private InstitutionMapper institutionMapper;

    public InstitutionDTO getInstitution(String institutionUuid) {
        Institution institution = institutionRepository.findOneByUuid(institutionUuid);
        if(institution == null) {
            throw new NotFoundException("Institution", institutionUuid);
        }
        return institutionMapper.toDTO(institution);
    }

    public List<InstitutionDTO> getAllInstitutions() {
        return institutionRepository
                .findAll()
                .stream()
                .map(institution -> institutionMapper.toDTO(institution))
                .collect(Collectors.toList());
    }

    public InstitutionDTO saveInstitution(InstitutionDTO institutionDTO) {

        Institution institution = institutionMapper.getInstitution(institutionDTO);
        institutionRepository.save(institution);

        return institutionMapper.toDTO(institution);
    }

    public InstitutionDTO updateInstitution(InstitutionDTO institutionDTO) {
        Institution example1 = new Institution(institutionDTO.getUuid());

        Optional<Institution> optionalInstitution = institutionRepository.findOne(Example.of(example1));

        if (optionalInstitution.isEmpty()) {
            throw  new NotFoundException("Institution", institutionDTO.getUuid());
        }

        Institution institution = optionalInstitution.get();

        institution.setName(institutionDTO.getName());
        institution.setDescription(institutionDTO.getDescription());
        institution.setLocation(institutionDTO.getLocation());
        institution.setCategory(institutionDTO.getCategory());
        institution.setEmail(institutionDTO.getEmail());
        institution.setPhone(institutionDTO.getPhone());
        institution.setUrl(institutionDTO.getUrl());
        institution.setLogo_url(institutionDTO.getLogo_url());
        institution.setBackground_url(institutionDTO.getBackground_url());

        institutionRepository.save(institution);
        return institutionMapper.toDTO(institution);
    }

    public InstitutionDTO deleteInstitution(String institutionUuid) {
        Institution example1 = new Institution(institutionUuid);

        Optional<Institution> optionalInstitution = institutionRepository.findOne(Example.of(example1));

        if (optionalInstitution.isEmpty()) {
            throw  new NotFoundException("Institution", institutionUuid);
        }

        Institution institution = optionalInstitution.get();
        institutionRepository.delete(institution);
        return institutionMapper.toDTO(institution);
    }
}
