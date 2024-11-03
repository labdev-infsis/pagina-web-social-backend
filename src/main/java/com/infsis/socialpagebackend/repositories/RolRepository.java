package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNameRole(String nameRole);
}