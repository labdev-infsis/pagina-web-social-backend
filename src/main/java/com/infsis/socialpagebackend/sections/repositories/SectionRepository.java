package com.infsis.socialpagebackend.sections.repositories;

import com.infsis.socialpagebackend.sections.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

    @Query("SELECT s FROM Section s WHERE s.uuid = ?1")
    Section findOneByUuid(String uuid);
}
