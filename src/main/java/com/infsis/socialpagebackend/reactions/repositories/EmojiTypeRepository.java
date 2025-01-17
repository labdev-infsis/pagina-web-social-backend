package com.infsis.socialpagebackend.reactions.repositories;

import com.infsis.socialpagebackend.reactions.models.EmojiType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmojiTypeRepository extends JpaRepository<EmojiType, Integer> {

    @Query("SELECT p FROM EmojiType p WHERE p.uuid = ?1")
    EmojiType findOneByUuid(String Uuid);
}
