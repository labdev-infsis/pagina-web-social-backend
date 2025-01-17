package com.infsis.socialpagebackend.medias.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name = "video_file")
public class VideoFile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
        VideoFile videoFile = (VideoFile) o;
        return Objects.equals(id, videoFile.id) && Objects.equals(uuid, videoFile.uuid) && Objects.equals(name, videoFile.name) && Objects.equals(url_resource, videoFile.url_resource) && Objects.equals(type, videoFile.type) && Objects.equals(status, videoFile.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, name, url_resource, type, status);
    }
}
