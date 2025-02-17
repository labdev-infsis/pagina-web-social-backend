package com.infsis.socialpagebackend.authentication.repositories;

import com.infsis.socialpagebackend.authentication.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.uuid = ?1")
    Users findOneByUuid(String Uuid);

    @Query("SELECT u FROM Users u WHERE u.id = ?1")
    Users findOneById(Integer id);

    //Método para poder buscar un usuario mediante su nombre
    Optional<Users> findByEmail(String email);

    //Método para poder verificar si un usuario existe en nuestra base de datos
    Boolean existsByEmail(String email);
}

