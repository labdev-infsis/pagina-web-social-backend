package com.infsis.socialpagebackend.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "document_file")
@Data
public class DocumentFile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    @Column(updatable = false, nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, length = 200)
    private String url;

    @Column(nullable = false, length = 200)
    private String url_resource;

    @Column(nullable = false, length = 20)
    private String type;

    @Column(nullable = false, length = 20)
    private String status;
}
