package com.infsis.socialpagebackend.sections.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infsis.socialpagebackend.authentication.models.Users;
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

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"section", "users"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE Article SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(updatable = false, unique = true, length = 36)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", referencedColumnName = "uuid")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uuid")
    private Users users;

    @Column(length = 1000)
    private String title;

    @Column(length = 3000)
    private String text;

    @Column()
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date date;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<ArticleMedia> article_medias;

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
        Article article = (Article) o;
        return deleted == article.deleted && Objects.equals(id, article.id) && Objects.equals(uuid, article.uuid) && Objects.equals(section, article.section) && Objects.equals(users, article.users) && Objects.equals(article_medias, article.article_medias) && Objects.equals(date, article.date) && Objects.equals(createdDate, article.createdDate) && Objects.equals(lastModifiedDate, article.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, section, users, article_medias, date, createdDate, lastModifiedDate, deleted);
    }
}
