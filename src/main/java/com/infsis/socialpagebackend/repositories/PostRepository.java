package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p WHERE p.group.uuid = ?1")
    List<Post> findByGroupUuid(String groupUuid);

    @Query("SELECT p FROM Post p WHERE p.uuid = ?1")
    Post findOneByUuid(String uuid);
}


