package com.infsis.socialpagebackend.authentication.repositories;

import com.infsis.socialpagebackend.authentication.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("select t from Token t inner join t.user u where u.id = :id and (t.isExpired = false and t.isRevoked = false)")
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}