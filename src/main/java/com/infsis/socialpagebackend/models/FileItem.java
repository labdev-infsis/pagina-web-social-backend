package com.infsis.socialpagebackend.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "file_item")
public class FileItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    @Column(nullable = false, length = 200)
    private String url_resource;

    @Column(nullable = false, length = 20)
    private String type;

    @Column(nullable = false, length = 20)
    private String status;

    public FileItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUrl_resource() {
        return url_resource;
    }

    public void setUrl_resource(String url_resource) {
        this.url_resource = url_resource;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileItem fileItem = (FileItem) o;
        return Objects.equals(id, fileItem.id) && Objects.equals(uuid, fileItem.uuid) && Objects.equals(url_resource, fileItem.url_resource) && Objects.equals(type, fileItem.type) && Objects.equals(status, fileItem.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, url_resource, type, status);
    }
}
