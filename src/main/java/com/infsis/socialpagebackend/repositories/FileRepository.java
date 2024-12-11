package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.FileItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileItem, Integer> {

    @Query("SELECT f FROM FileItem f WHERE f.uuid = ?1")
    FileItem findOneByUuid(String uuid);
}
