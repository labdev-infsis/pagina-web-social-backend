package com.infsis.socialpagebackend.models;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comment_config")
public class CommentConfig {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    @Column(nullable = false, length = 200)
    private String configuration;

    public CommentConfig() {
    }

    public CommentConfig(Integer id) {
        this.id = id;
    }

    public CommentConfig(String uuid) {
        this.uuid = uuid;
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

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    @PrePersist
    public void initializeUuid() {
        this.setUuid(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentConfig that = (CommentConfig) o;
        return id.equals(that.getId())
                && uuid.equals(that.getUuid())
                && configuration.equals(that.getConfiguration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, configuration);
    }
}
