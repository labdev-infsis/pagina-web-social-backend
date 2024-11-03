package com.infsis.socialpagebackend.models;

import jakarta.persistence.*;
import java.util.Set;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameRole;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    // Getters and setters
}