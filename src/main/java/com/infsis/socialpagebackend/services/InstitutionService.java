package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.dtos.InstitutionDTO;
import com.infsis.socialpagebackend.dtos.InstitutionMapper;
import com.infsis.socialpagebackend.dtos.MediaItemDTO;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import com.infsis.socialpagebackend.models.Institution;
import com.infsis.socialpagebackend.models.Media;
import com.infsis.socialpagebackend.models.Post;
import com.infsis.socialpagebackend.repositories.InstitutionRepository;
import com.infsis.socialpagebackend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InstitutionService {

    private static final String INST_PROFILE_PHOTO_DIR = System.getProperty("user.dir") + "/storage/institution/profile/photos/";
    private static final String IMAGES_INSTITUTION_PROFILE_PATH = "/api/v1/images/inst-profile/";
    private static final String INST_COVER_DIR = System.getProperty("user.dir") + "/storage/institution/cover/photos/";
    private static final String IMAGES_INSTITUTION_COVER_PATH = "/api/v1/images/inst-cover/";

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private InstitutionMapper institutionMapper;

    @Autowired
    private ImageStorageService imageStorageService;

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
        Optional<Institution> optionalInstitution = findInstitution(institutionDTO.getUuid());

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
        Optional<Institution> optionalInstitution = findInstitution(institutionUuid);

        Institution institution = optionalInstitution.get();
        institutionRepository.delete(institution);
        return institutionMapper.toDTO(institution);
    }

    private Optional<Institution> findInstitution(String institutionUuid) {
        Institution example1 = new Institution(institutionUuid);
        Optional<Institution> optionalInstitution = institutionRepository.findOne(Example.of(example1));

        if (optionalInstitution.isEmpty()) {
            throw  new NotFoundException("Institution", institutionUuid);
        }

        return optionalInstitution;
    }

    /*
    public InstitutionDTO saveProfileImage(String institutionUuid, List<MultipartFile> image) throws IOException {
        Optional<Institution> optionalInstitution = findInstitution(institutionUuid);

        ImageFileDTO file = saveImage(image, INST_PROFILE_PHOTO_DIR, IMAGES_INSTITUTION_PROFILE_PATH);

        Institution institution = optionalInstitution.get();
        institution.setLogo_url(file.getUrlResource());

        institutionRepository.save(institution);
        return institutionMapper.toDTO(institution);
    }

    public InstitutionDTO saveCoverImage(String institutionUuid, List<MultipartFile> image) throws IOException {

        Optional<Institution> optionalInstitution = findInstitution(institutionUuid);

        ImageFileDTO file = saveImage(image, INST_COVER_DIR, IMAGES_INSTITUTION_COVER_PATH);

        Institution institution = optionalInstitution.get();
        institution.setBackground_url(file.getUrlResource());

        institutionRepository.save(institution);
        return institutionMapper.toDTO(institution);
    }

    private ImageFileDTO saveImage(List<MultipartFile> image, String imageDirectory, String imageRoute) throws IOException {
        List<ImageFileDTO> fileItemDTO = imageStorageService.storeImages(image, imageDirectory, imageRoute);
        return fileItemDTO.get(0);
    }
     */
    public List<MediaItemDTO> getMediasInstitution(String institutionUuid, String type) {
        List<Post> posts = postRepository.findAll();
        List<MediaItemDTO> mediaItems = new ArrayList<>();
        for (Post post : posts) {
            if (post.getInstitution().getUuid().equals(institutionUuid)) {
                for (Media media : post.getContent().getMedia()) {
                    if (type.equalsIgnoreCase(media.getFile_type())) {
                        MediaItemDTO mediaItemDTO = new MediaItemDTO();
                        mediaItemDTO.setUuid_post(post.getUuid());
                        mediaItemDTO.setPath(media.getFile_path());
                        mediaItems.add(mediaItemDTO);
                    }
                }
            }
        }
        return mediaItems;
    }
}
