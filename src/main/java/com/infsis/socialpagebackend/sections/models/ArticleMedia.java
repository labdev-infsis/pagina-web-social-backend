package com.infsis.socialpagebackend.sections.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"article"})
@SQLDelete(sql = "UPDATE ArticleMedia SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Table(name = "article_media")
public class ArticleMedia {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "uuid")
    private Article article;

    @Column(nullable = false, length = 150)
    private String file_name;

    @Column(nullable = false, length = 150)
    private String file_path;

    @Column(nullable = false, length = 30)
    private String file_type;

    @Column(nullable = false, length = 10)
    private Integer number;

    @CreatedDate
    @Column(updatable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(updatable = false)
    private Date lastModifiedDate;

    @Column(nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT '0'")
    private boolean deleted;

    @PrePersist
    public void initializeUuid() {
        this.setUuid(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleMedia that = (ArticleMedia) o;
        return Objects.equals(id, that.id) && Objects.equals(uuid, that.uuid) && Objects.equals(article, that.article) && Objects.equals(file_name, that.file_name) && Objects.equals(file_path, that.file_path) && Objects.equals(file_type, that.file_type) && Objects.equals(number, that.number) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, article, file_name, file_path, file_type, number, createdDate, lastModifiedDate);
    }
}
