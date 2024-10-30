package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.ContactInfoDTO;
import com.infsis.socialpagebackend.dtos.ContactInfoMapper;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.ContactInfo;
import com.infsis.socialpagebackend.repositories.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ContactInfoService {

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    @Autowired
    private ContactInfoMapper contactInfoMapper;

    public ContactInfoDTO getContactInfo(String contactInfoUuid) {
        ContactInfo contactInfo = contactInfoRepository.findOneByUuid(contactInfoUuid);
        if(contactInfo == null) {
            throw new NotFoundException("Contact Information", contactInfoUuid);
        }
        return contactInfoMapper.toDTO(contactInfo);
    }

    public List<ContactInfoDTO> getAllContactInfos() {
        return contactInfoRepository
                .findAll()
                .stream()
                .map(contactInfo -> contactInfoMapper.toDTO(contactInfo))
                .collect(Collectors.toList());
    }

    public ContactInfoDTO saveContactInfo(ContactInfoDTO contactInfoDTO) {

        ContactInfo contactInfo = contactInfoMapper.getContactInfo(contactInfoDTO);
        contactInfoRepository.save(contactInfo);

        return contactInfoMapper.toDTO(contactInfo);
    }

    public ContactInfoDTO updateContactInfo(ContactInfoDTO contactInfoDTO) {
        ContactInfo example1 = new ContactInfo(contactInfoDTO.getUuid());

        Optional<ContactInfo> optionalContactInfo = contactInfoRepository.findOne(Example.of(example1));

        if (optionalContactInfo.isEmpty()) {
            throw  new NotFoundException("Contact Information", contactInfoDTO.getUuid());
        }

        ContactInfo contactInfo = optionalContactInfo.get();

        contactInfo.setName(contactInfoDTO.getName());
        contactInfo.setDescription(contactInfoDTO.getDescription());
        contactInfo.setCategory(contactInfoDTO.getCategory());
        contactInfo.setEmail(contactInfoDTO.getEmail());
        contactInfo.setPhone(contactInfoDTO.getPhone());
        contactInfo.setUrl(contactInfoDTO.getUrl());
        contactInfo.setLogo_url(contactInfoDTO.getLogo_url());
        contactInfo.setBackground_url(contactInfoDTO.getBackground_url());

        contactInfoRepository.save(contactInfo);
        return contactInfoMapper.toDTO(contactInfo);
    }

    public ContactInfoDTO deleteContactInfo(String contactInfoUuid) {
        ContactInfo example1 = new ContactInfo(contactInfoUuid);

        Optional<ContactInfo> optionalContactInfo = contactInfoRepository.findOne(Example.of(example1));

        if (optionalContactInfo.isEmpty()) {
            throw  new NotFoundException("Contact Information", contactInfoUuid);
        }

        ContactInfo contactInfo = optionalContactInfo.get();
        contactInfoRepository.delete(contactInfo);
        return contactInfoMapper.toDTO(contactInfo);
    }
}
