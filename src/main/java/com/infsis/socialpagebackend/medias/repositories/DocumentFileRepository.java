package com.infsis.socialpagebackend.medias.repositories;

import com.infsis.socialpagebackend.medias.models.DocumentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentFileRepository extends JpaRepository<DocumentFile, Integer> {

    @Query("SELECT f FROM DocumentFile f WHERE f.uuid = ?1")
    DocumentFile findOneByUuid(String uuid);

    Optional<DocumentFile> findByUuid(String uuid);
}
