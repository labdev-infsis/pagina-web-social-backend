package com.infsis.socialpagebackend.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 80)
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 80)
    private String lastName;

    @Column(length = 15)
    private String phone;

    @Column(length = 100)
    private String photo_profile_path;

    @Column(length = 100)
    private String photo_portada_path;
    //Usamos fetchType en EAGER para que cada vez que se acceda o se extraiga un usuario de la BD, este se traiga todos sus roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    /*Con JoinTable estaremos creando una tabla que unirá la tabla de usuario y role, con lo cual tendremos un total de 3 tablas
    relacionadas en la tabla "usuarios_roles", a través de sus columnas usuario_id que apuntara al ID de la tabla usuario
    y role_id que apuntara al Id de la tabla role */
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user")
            ,inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_role"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @PrePersist
    public void initializeUuid() {
        this.setUuid(UUID.randomUUID().toString());
    }
}