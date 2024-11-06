package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Institution;
import com.infsis.socialpagebackend.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {

    @Query("SELECT m FROM Media m WHERE m.uuid = ?1")
    Media findOneByUuid(String Uuid);
}
