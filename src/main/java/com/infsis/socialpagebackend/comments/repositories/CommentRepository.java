package com.infsis.socialpagebackend.comments.repositories;

import com.infsis.socialpagebackend.comments.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findByUuid(String uuid);

    @Query("SELECT pr FROM Comment pr WHERE pr.post.uuid = ?1")
    List<Comment> findByPostId(String postUuid);
}