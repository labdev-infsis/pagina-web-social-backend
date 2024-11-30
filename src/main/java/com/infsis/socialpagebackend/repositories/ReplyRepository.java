package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    Reply findByUuid(String uuid);
    List<Reply> findByCommentUuid(String commentUuid);
}