package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Institution;
import com.infsis.socialpagebackend.models.Followers;
import com.infsis.socialpagebackend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstitutionFollowerRepository extends JpaRepository<Followers, Long> {
    Optional<Followers> findByInstitutionAndUser(Institution institution, Users user);
}