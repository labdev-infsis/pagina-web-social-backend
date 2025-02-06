package com.infsis.socialpagebackend.reactions.repositories;

import com.infsis.socialpagebackend.reactions.models.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostReactionRepository extends JpaRepository<PostReaction, Integer> {

    @Query("SELECT pr FROM PostReaction pr WHERE pr.uuid = ?1")
    PostReaction findOneByUuid(String uuid);

    @Query("SELECT pr FROM PostReaction pr WHERE pr.post.uuid = ?1")
    List<PostReaction> findByPostId(String postUuid);

    @Query("SELECT pr FROM PostReaction pr WHERE pr.post.uuid = ?1 and pr.users.uuid = ?2")
    PostReaction findByPostUuidAndUserUuid(String postUuid, String userUuid);

    /*
    @Query("SELECT pr FROM PostReaction pr WHERE pr.users.uuid = ?1")
    PostReaction findByUserUuid(String userUuid);
     */
}
