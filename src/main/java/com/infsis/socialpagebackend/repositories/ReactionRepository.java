package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

    @Query("SELECT p FROM Post p WHERE p.uuid = ?1")
    Reaction findOneByUuid(String Uuid);
}
