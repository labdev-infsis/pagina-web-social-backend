package com.infsis.socialpagebackend.posts.repositories;

import com.infsis.socialpagebackend.posts.models.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p WHERE LOWER(p.content.text.text) LIKE LOWER(CONCAT('%', :text, '%')) ORDER BY p.createdDate DESC")
    List<Post> searchPostsByText(@Param("text") String text);    

    @Query("SELECT p FROM Post p WHERE p.uuid = ?1")
    Post findOneByUuid(String Uuid);
}
