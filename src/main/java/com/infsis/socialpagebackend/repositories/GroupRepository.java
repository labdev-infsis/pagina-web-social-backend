package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query("SELECT g FROM Group g WHERE g.uuid = ?1")
    Group findOneByUuid(String uuid);

    @Query("SELECT g FROM Group g WHERE g.name = ?1")
    Optional<Group> findOneByName(String name);

}
