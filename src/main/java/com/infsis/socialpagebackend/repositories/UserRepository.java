package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Institution;
import com.infsis.socialpagebackend.models.Post;
import com.infsis.socialpagebackend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.uuid = ?1")
    Users findOneByUuid(String Uuid);

    @Query("SELECT u FROM Users u WHERE u.id = ?1")
    Users findOneById(Integer id);
}
