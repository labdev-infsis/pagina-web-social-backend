package com.infsis.socialpagebackend.medias.repositories;

import com.infsis.socialpagebackend.medias.models.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageFileRepository extends JpaRepository<ImageFile, Integer> {

    @Query("SELECT f FROM ImageFile f WHERE f.uuid = ?1")
    ImageFile findOneByUuid(String uuid);

    Optional<ImageFile> findByUuid(String uuid);
}
