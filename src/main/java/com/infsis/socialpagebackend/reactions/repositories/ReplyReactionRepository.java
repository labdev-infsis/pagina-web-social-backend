package com.infsis.socialpagebackend.reactions.repositories;

import com.infsis.socialpagebackend.reactions.models.ReplyReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyReactionRepository extends JpaRepository<ReplyReaction, Integer> {
  //  @Query("SELECT pr FROM ReplyReaction pr WHERE pr.uuid = ?1")
   // PostReaction findOneByUuid(String uuid);
    ReplyReaction findOneByUuid(String uuid);
  @Query("SELECT rr FROM ReplyReaction rr WHERE rr.reply.uuid = ?1")
  List<ReplyReaction> findByReplyId(String replyUuid);
}