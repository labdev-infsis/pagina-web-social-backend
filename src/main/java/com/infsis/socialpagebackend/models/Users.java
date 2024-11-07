package com.infsis.socialpagebackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUsuario;
    private String email;
    private String password;
    private String name;
    private String lastName;
    @Column(nullable = true)
    private String phone;
    @Column(nullable = true)
    private String photo_profile_path;
    @Column(nullable = true)
    private String photo_portada_path;
    //Usamos fetchType en EAGER para que cada vez que se acceda o se extraiga un usuario de la BD, este se traiga todos sus roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    /*Con JoinTable estaremos creando una tabla que unirá la tabla de usuario y role, con lo cual tendremos un total de 3 tablas
    relacionadas en la tabla "usuarios_roles", a través de sus columnas usuario_id que apuntara al ID de la tabla usuario
    y role_id que apuntara al Id de la tabla role */
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user")
            ,inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_role"))
    private List<Role> roles = new ArrayList<>();
}