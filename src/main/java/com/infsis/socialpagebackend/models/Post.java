package com.infsis.socialpagebackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE Post SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Table(name = "post")

public class Post implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "institution_id", referencedColumnName = "uuid", nullable = false)
    private Institution institution;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uuid", nullable = false)
    private Users users;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "content_id", referencedColumnName = "uuid", nullable = false, unique = true)
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comment_config_id", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CommentConfig comment_conf;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date post_date;

    @CreatedDate
    @Column(updatable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(updatable = false)
    private Date lastModifiedDate;

    @Column(nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT '0'")
    private boolean deleted;

    List<Comment> comments = new ArrayList<>();

    @Override
    public boolean isNew() {
        return true;
    }

    public Post() {
    }
    public List<Comment> getComments() {
        return comments;
    }
    public Post(Integer id) {
        this.id = id;
    }

    public Post(String uuid) {
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

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public CommentConfig getComment_conf() {
        return comment_conf;
    }

    public void setComment_conf(CommentConfig comment_conf) {
        this.comment_conf = comment_conf;
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @PrePersist
    public void initializeUuid() {
        this.setUuid(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id != null && id.equals(post.getId())
                && uuid.equals(post.getUuid())
                && institution.getUuid().equals(post.getInstitution().getUuid())
                && users.getUuid().equals(post.getUser().getUuid())
                && content.equals(post.getContent())
                && post_date.equals(post.getPost_date());
    }

    @Override
    public int hashCode() {
        return  getClass().hashCode();
    }
}
