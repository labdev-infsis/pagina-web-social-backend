package com.infsis.socialpagebackend.sections.mappers;

import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.institutions.models.Institution;
import com.infsis.socialpagebackend.sections.dtos.SectionDTO;
import com.infsis.socialpagebackend.sections.models.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SectionMapper {

    @Autowired
    private ArticleMapper articleMapper;

    public SectionDTO toDTO(Section section) {

        SectionDTO sectionDTO = new SectionDTO();

        sectionDTO.setUuid(section.getUuid());
        sectionDTO.setName(section.getName());
        sectionDTO.setDate(section.getDate());
        sectionDTO.setUser_id(section.getUsers().getUuid());
        sectionDTO.setInstitution_id(section.getInstitution().getUuid());

        sectionDTO.setArticles(
                section.getArticles()
                        .stream()
                        .map(article -> articleMapper.toDTO(article))
                        .collect(Collectors.toList())
        );

        return sectionDTO;
    }

    public Section getSection(SectionDTO sectionDTO, Institution institution, Users user) {

        Section section = new Section();

        section.setInstitution(institution);
        section.setUsers(user);
        section.setName(sectionDTO.getName());
        section.setDate(sectionDTO.getDate());

        return section;
    }
}
