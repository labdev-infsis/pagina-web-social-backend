package com.infsis.socialpagebackend.sections.services;

import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.authentication.repositories.UserRepository;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.institutions.models.Institution;
import com.infsis.socialpagebackend.institutions.repositories.InstitutionRepository;
import com.infsis.socialpagebackend.sections.dtos.ArticleDTO;
import com.infsis.socialpagebackend.sections.dtos.SectionDTO;
import com.infsis.socialpagebackend.sections.mappers.ArticleMapper;
import com.infsis.socialpagebackend.sections.mappers.ArticleMediaMapper;
import com.infsis.socialpagebackend.sections.mappers.SectionMapper;
import com.infsis.socialpagebackend.sections.models.Article;
import com.infsis.socialpagebackend.sections.models.Section;
import com.infsis.socialpagebackend.sections.repositories.ArticleMediaRepository;
import com.infsis.socialpagebackend.sections.repositories.ArticleRepository;
import com.infsis.socialpagebackend.sections.repositories.SectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SectionService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private SectionMapper sectionMapper;

    @Autowired
    private ArticleMediaMapper mediaMapper;

    @Autowired
    private ArticleMediaRepository articleMediaRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private UserRepository userRepository;



    public SectionDTO getSection(String sectionUuid) {
        Section section = sectionRepository.findOneByUuid(sectionUuid);

        if(section == null || section.isDeleted()) {
            throw new NotFoundException("Section", sectionUuid);
        }
        //List<Article> articles = getPostReactionCounterDTO(article);

        return sectionMapper.toDTO(section);
    }

    public List<SectionDTO> getAllSections() {
        return sectionRepository
                .findAll()
                .stream()
                .filter(section -> !section.isDeleted())
                .map(section -> sectionMapper.toDTO(section))
                .collect(Collectors.toList());
    }

    public SectionDTO getSectionByName(String name) {
        return sectionRepository
                .findByName(name)
                .map(section -> sectionMapper.toDTO(section))
                .orElseThrow(() -> new NotFoundException("Section not found with name: ", name));
    }

    public SectionDTO saveSection(SectionDTO sectionDTO) {

        Institution institution = institutionRepository.findOneByUuid(sectionDTO.getInstitution_id());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found: ", email));

        log.info("User id :" + user.getUuid());

        Section section = new Section();
        SectionDTO resDTO = new SectionDTO();

        if (user != null) {
            List<Article> articles = saveArticles(sectionDTO.getArticles(), section, user);

            section = sectionMapper.getSection(sectionDTO, institution, user);
            section.setArticles(articles);

            sectionRepository.save(section);

            resDTO = sectionMapper.toDTO(section);

        }

        return resDTO;
    }

    public SectionDTO deleteSection(String sectionUuid) {
        Section section = sectionRepository.findOneByUuid(sectionUuid);
        section.setDeleted(true);
        sectionRepository.save(section);
        return sectionMapper.toDTO(section);
    }

    private List<Article> saveArticles(List<ArticleDTO> articleDTOS, Section section, Users user){
        List<Article> articles = new ArrayList<>();
        if(articleDTOS != null ) {
            articles = articleDTOS
                    .stream()
                    .map(article -> articleMapper.getArticle(article, section, user))
                    .collect(Collectors.toList());

            articles.stream().forEach(
                    (article) -> articleRepository.save(article)
            );
        }
        return articles;
    }

    public SectionDTO updateSection(String sectionUuid, SectionDTO sectionDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found: ", email));

        log.info("User id :" + user.getUuid());

        Section foundSection = sectionRepository.findOneByUuid(sectionUuid);
        if (foundSection == null || foundSection.isDeleted()) {
            throw new NotFoundException("Section", sectionUuid);
        }

        if (foundSection.getArticles() != null) {

            List<Article> articles = saveArticles(sectionDTO.getArticles(), foundSection, user);
            foundSection.setArticles(articles);
        }

        foundSection.setDate(sectionDTO.getDate());
        foundSection.setName(sectionDTO.getName());

        Section updatedSection = sectionRepository.save(foundSection);

        return sectionMapper.toDTO(updatedSection);
    }




}
