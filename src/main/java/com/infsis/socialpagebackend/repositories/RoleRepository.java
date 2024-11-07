package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    //Optional<Rol> findByNameRole(String nameRole);
    Optional<Role> findByName(String name);
}