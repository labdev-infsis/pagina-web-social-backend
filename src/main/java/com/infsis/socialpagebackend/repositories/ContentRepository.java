package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

    @Query("SELECT c FROM Content c WHERE c.uuid = ?1")
    Content findOneByUuid(String Uuid);
}
