package com.infsis.socialpagebackend.repositories;

import com.infsis.socialpagebackend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    //Método para poder buscar un usuario mediante su nombre
    Optional<Users> findByEmail(String email);

    //Método para poder verificar si un usuario existe en nuestra base de datos
    Boolean existsByEmail(String email);
}

