package com.infsis.socialpagebackend.dtos;

import com.infsis.socialpagebackend.models.ContactInfo;
import org.springframework.stereotype.Component;

@Component
public class ContactInfoMapper {

    public ContactInfoDTO toDTO(ContactInfo contactInfo) {
        ContactInfoDTO contactInfoDTO = new ContactInfoDTO();
        contactInfoDTO.setUuid(contactInfo.getUuid());
        contactInfoDTO.setName(contactInfo.getName());
        contactInfoDTO.setDescription(contactInfo.getDescription());
        contactInfoDTO.setCategory(contactInfo.getCategory());
        contactInfoDTO.setEmail(contactInfo.getEmail());
        contactInfoDTO.setPhone(contactInfo.getPhone());
        contactInfoDTO.setUrl(contactInfo.getUrl());
        contactInfoDTO.setLogo_url(contactInfo.getLogo_url());
        contactInfoDTO.setBackground_url(contactInfo.getBackground_url());
        return contactInfoDTO;
    }

    public ContactInfo getContactInfo(ContactInfoDTO contactInfoDTO) {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setUuid(contactInfoDTO.getUuid());
        contactInfo.setName(contactInfoDTO.getName());
        contactInfo.setDescription(contactInfoDTO.getDescription());
        contactInfo.setCategory(contactInfoDTO.getCategory());
        contactInfo.setEmail(contactInfoDTO.getEmail());
        contactInfo.setPhone(contactInfoDTO.getPhone());
        contactInfo.setUrl(contactInfoDTO.getUrl());
        contactInfo.setLogo_url(contactInfoDTO.getLogo_url());
        contactInfo.setBackground_url(contactInfoDTO.getBackground_url());
        return contactInfo;
    }

}
