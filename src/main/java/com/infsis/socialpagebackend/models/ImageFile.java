package com.infsis.socialpagebackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name = "image_file")
public class ImageFile {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 200)
    private String url_resource;

    @Column(nullable = false, length = 20)
    private String type;

    @Column(nullable = false, length = 20)
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageFile imageFile = (ImageFile) o;
        return Objects.equals(id, imageFile.id) && Objects.equals(uuid, imageFile.uuid) && Objects.equals(url_resource, imageFile.url_resource) && Objects.equals(type, imageFile.type) && Objects.equals(status, imageFile.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, url_resource, type, status);
    }
}
