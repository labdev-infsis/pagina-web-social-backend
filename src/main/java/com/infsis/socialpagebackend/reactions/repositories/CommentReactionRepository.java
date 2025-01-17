package com.infsis.socialpagebackend.reactions.repositories;

import com.infsis.socialpagebackend.reactions.models.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentReactionRepository extends JpaRepository<CommentReaction, Integer> {

    @Query("SELECT cr FROM CommentReaction cr WHERE cr.uuid = ?1")
    CommentReaction findOneByUuid(String uuid);

    @Query("SELECT cr FROM CommentReaction cr WHERE cr.comment.uuid = ?1")
    List<CommentReaction> findByCommentId(String commentUuid);

}