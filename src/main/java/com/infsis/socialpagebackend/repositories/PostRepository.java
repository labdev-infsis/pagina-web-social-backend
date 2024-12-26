package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Post;

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

    @Query("SELECT m.file_path FROM Post p " +
    "JOIN p.content.media m " +
    "WHERE p.institution.uuid = :institutionUuid " +
    "ORDER BY p.post_date DESC")
List<String> findMediaUrlsByInstitution(@Param("institutionUuid") String institutionUuid);

@Query("SELECT m.file_path FROM Post p " +
"JOIN p.content.media m " +
"WHERE p.institution.uuid = :institutionUuid " +
"AND m.file_type = 'video' " +
"ORDER BY p.post_date DESC")
List<String> findAllVideoUrlsByInstitution(@Param("institutionUuid") String institutionUuid);


}
