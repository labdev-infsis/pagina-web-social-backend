package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Reaction {

    @Query("SELECT p FROM Post p WHERE p.uuid = ?1")
    Post findOneByUuid(String Uuid);
}
