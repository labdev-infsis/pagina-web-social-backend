package com.infsis.socialpagebackend.services;

import com.infsis.socialpagebackend.models.Institution;
import com.infsis.socialpagebackend.models.Followers;
import com.infsis.socialpagebackend.models.Users;
import com.infsis.socialpagebackend.repositories.InstitutionFollowerRepository;
import com.infsis.socialpagebackend.repositories.InstitutionRepository;
import com.infsis.socialpagebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.infsis.socialpagebackend.exceptions.NotFoundException;
import java.util.Date;
import java.util.Optional;

@Service
public class FollowerService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstitutionFollowerRepository institutionFollowerRepository;

    public void followInstitution(String institutionUuid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con email: ", email));

        Institution institution = institutionRepository.findOneByUuid(institutionUuid);
        if (institution == null) {
            throw new NotFoundException("Institución no encontrada con UUID: ", institutionUuid);
        }

        Optional<Followers> existingFollower = institutionFollowerRepository.findByInstitutionAndUser(institution, user);
        if (existingFollower.isPresent()) {
            throw new IllegalArgumentException("Ya sigues esta página.");
        }

        Followers follower = new Followers();
        follower.setInstitution(institution);
        follower.setUser(user);
        follower.setFollowedSince(new Date());

        institutionFollowerRepository.save(follower);
    }
    public void unfollowInstitution(String institutionUuid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con email: ", email));

        Institution institution = institutionRepository.findOneByUuid(institutionUuid);
        if (institution == null) {
            throw new NotFoundException("Institución no encontrada con UUID: ", institutionUuid);
        }

        Followers follower = institutionFollowerRepository.findByInstitutionAndUser(institution, user)
                .orElseThrow(() -> new IllegalArgumentException("No sigues esta página."));

        institutionFollowerRepository.delete(follower);
    }
}