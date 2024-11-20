package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.EmojiType;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmojiTypeRepository extends JpaRepository<EmojiType, Integer> {

    @Query("SELECT p FROM EmojiType p WHERE p.uuid = ?1")
    EmojiType findOneByUuid(String Uuid);
}
