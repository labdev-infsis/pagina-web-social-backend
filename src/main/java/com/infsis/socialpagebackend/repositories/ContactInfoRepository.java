package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer> {

    @Query("SELECT ci FROM ContactInfo ci WHERE ci.uuid = ?1")
    ContactInfo findOneByUuid(String Uuid);
}
