package com.infsis.socialpagebackend.sections.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infsis.socialpagebackend.authentication.models.Users;
import com.infsis.socialpagebackend.institutions.models.Institution;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE Section SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "institution_id", referencedColumnName = "uuid", nullable = false)
    private Institution institution;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uuid", nullable = false)
    private Users users;

    @Column(nullable = false, length = 1000)
    private String name;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date date;

    @OneToMany(mappedBy = "section", cascade = CascadeType.REMOVE)
    private List<Article> articles;

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
        Section section = (Section) o;
        return deleted == section.deleted && Objects.equals(id, section.id) && Objects.equals(uuid, section.uuid) && Objects.equals(institution, section.institution) && Objects.equals(users, section.users) && Objects.equals(name, section.name) && Objects.equals(date, section.date) && Objects.equals(articles, section.articles) && Objects.equals(createdDate, section.createdDate) && Objects.equals(lastModifiedDate, section.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, institution, users, name, date, articles, createdDate, lastModifiedDate, deleted);
    }
}
