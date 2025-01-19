package com.infsis.socialpagebackend.replies.repository;

import com.infsis.socialpagebackend.replies.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    Reply findByUuid(String uuid);
    List<Reply> findByCommentUuid(String commentUuid);
}