package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.PostGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostGroupRepository extends JpaRepository<PostGroup, Integer> {

    @Query("SELECT pg FROM PostGroup pg WHERE pg.uuid = ?1")
    PostGroup findOneByUuid(String uuid);

}
