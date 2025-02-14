package com.infsis.socialpagebackend.posts.repositories;

import com.infsis.socialpagebackend.posts.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {

    @Query("SELECT m FROM Media m WHERE m.uuid = ?1")
    Media findOneByUuid(String Uuid);


    @Modifying
    @Transactional
    @Query("DELETE FROM Media m WHERE m.file_path = ?1")
    void deleteByFilePath(String filePath);
}
