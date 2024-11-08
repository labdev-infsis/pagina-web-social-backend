package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text, Integer> {

    @Query("SELECT t FROM Text t WHERE t.uuid = ?1")
    Text findOneByUuid(String Uuid);
}
