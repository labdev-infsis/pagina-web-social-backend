package com.infsis.socialpagebackend.controllers;

import com.infsis.socialpagebackend.dtos.ContactInfoDTO;
import com.infsis.socialpagebackend.services.ContactInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact-info")
@Validated
public class ContactInfoController {

    @Autowired
    private ContactInfoService contactInfoService;

    @GetMapping("/{contactInfoUuid}")
    public ContactInfoDTO get(@PathVariable String contactInfoUuid) {
        return contactInfoService.getContactInfo(contactInfoUuid);
    }

    @GetMapping
    public List<ContactInfoDTO> getAll() {
        return contactInfoService.getAllContactInfos();
    }

    @PostMapping
    public ContactInfoDTO create(@Valid @RequestBody ContactInfoDTO contactInfoDTO) {
        return contactInfoService.saveContactInfo(contactInfoDTO);
    }

    @PutMapping("/{contactInfoUuid}")
    public ContactInfoDTO update(@Valid @RequestBody ContactInfoDTO contactInfoDTO) {
        return contactInfoService.updateContactInfo(contactInfoDTO);
    }

    @DeleteMapping("/{contactInfoUuid}")
    public ContactInfoDTO delete(@PathVariable String contactInfoUuid) {
        return contactInfoService.deleteContactInfo(contactInfoUuid);
    }

}
