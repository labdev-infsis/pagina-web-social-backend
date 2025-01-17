package com.infsis.socialpagebackend.social_networks.repositories;

import com.infsis.socialpagebackend.social_networks.models.SocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Integer> {

    @Query("SELECT sn FROM SocialNetwork sn WHERE sn.uuid = ?1")
    SocialNetwork findOneByUuid(String Uuid);

    @Query("SELECT sn FROM SocialNetwork sn WHERE sn.institution.uuid = ?1")
    List<SocialNetwork> findByInstitutionUuid(String institutionUuid);
}
