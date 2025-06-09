package com.infsis.socialpagebackend.reactions.repositories;

import com.infsis.socialpagebackend.reactions.models.ReplyReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyReactionRepository extends JpaRepository<ReplyReaction, Integer> {

    @Query("SELECT rr FROM ReplyReaction rr WHERE rr.uuid = ?1")
    Optional<ReplyReaction> findByUuid(String uuid); // ✅ ESTA LÍNEA ES CLAVE

    @Query("SELECT rr FROM ReplyReaction rr WHERE rr.reply.uuid = ?1")
    List<ReplyReaction> findByReplyId(String replyUuid);

    @Query("SELECT cr FROM ReplyReaction cr WHERE cr.reply.uuid = ?1 AND cr.user.uuid = ?2")
    Optional<ReplyReaction> findByReplyUuidAndUserUuid(String replyUuid, String userUuid);

}
