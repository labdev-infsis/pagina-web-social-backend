package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.CommentConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentConfigRepository extends JpaRepository<CommentConfig, Integer> {

    @Query("SELECT c FROM CommentConfig c WHERE c.uuid = ?1")
    CommentConfig findOneByUuid(String Uuid);

    @Query("SELECT c FROM CommentConfig c WHERE c.id = ?1")
    CommentConfig findOneById(Integer id);

}
