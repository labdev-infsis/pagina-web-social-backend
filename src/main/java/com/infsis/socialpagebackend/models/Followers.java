package com.infsis.socialpagebackend.models;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "followers")
public class Followers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false)
    private Institution institution;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date followedSince;

}