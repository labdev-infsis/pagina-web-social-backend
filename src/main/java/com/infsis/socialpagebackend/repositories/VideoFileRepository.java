package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoFileRepository extends JpaRepository<VideoFile, Integer> {

    @Query("SELECT f FROM VideoFile f WHERE f.uuid = ?1")
    VideoFile findOneByUuid(String uuid);

    Optional<VideoFile> findByUuid(String uuid);
}
