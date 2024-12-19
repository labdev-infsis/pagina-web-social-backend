package com.infsis.socialpagebackend.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "content_id", referencedColumnName = "uuid")
    private Content content;

    @Column(nullable = false, length = 150)
    private String file_name;

    @Column(nullable = false, length = 100)
    private String file_path;

    @Column(nullable = false, length = 10)
    private String file_type;

    @Column(nullable = false, length = 10)
    private Integer number;

    @CreatedDate
    @Column(updatable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(updatable = false)
    private Date lastModifiedDate;

    @PrePersist
    public void initializeUuid() {
        this.setUuid(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return Objects.equals(id, media.id) && Objects.equals(uuid, media.uuid) && Objects.equals(content, media.content) && Objects.equals(file_path, media.file_path) && Objects.equals(file_type, media.file_type) && Objects.equals(number, media.number) && Objects.equals(createdDate, media.createdDate) && Objects.equals(lastModifiedDate, media.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, content, file_path, file_type, number, createdDate, lastModifiedDate);
    }
}
