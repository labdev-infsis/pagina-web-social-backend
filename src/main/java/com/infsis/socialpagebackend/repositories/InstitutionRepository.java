package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstitutionRepository extends JpaRepository<Institution, Integer> {

    @Query("SELECT i FROM Institution i WHERE i.uuid = ?1")
    Institution findOneByUuid(String Uuid);
}
