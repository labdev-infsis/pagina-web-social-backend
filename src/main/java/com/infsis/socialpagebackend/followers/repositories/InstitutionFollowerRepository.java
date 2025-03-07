package com.infsis.socialpagebackend.followers.repositories;

import com.infsis.socialpagebackend.institutions.models.Institution;
import com.infsis.socialpagebackend.followers.models.Followers;
import com.infsis.socialpagebackend.authentication.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InstitutionFollowerRepository extends JpaRepository<Followers, Long> {
    Optional<Followers> findByInstitutionAndUser(Institution institution, Users user);
    long countByInstitution(Institution institution);
    List<Followers> findByInstitution(Institution institution);
}