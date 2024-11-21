package com.infsis.socialpagebackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infsis.socialpagebackend.models.Group;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

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

  

    @ManyToOne
    @JoinColumn(name = "comment_config_id", referencedColumnName = "uuid", nullable = false)
    private CommentConfig comment_conf;
   
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "content_id", referencedColumnName = "uuid", nullable = false, unique = true)
    private Content content;


    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date post_date;


  
  
    @LastModifiedDate
    @Column(updatable = false)
    private Date lastModifiedDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "BOOLEAN NOT NULL DEFAULT '0'")
    private boolean deleted;

    @Column(nullable = false, length = 255)
    private String title;
   
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "uuid", nullable = true)
    private Group group;
    
    @Override
    public boolean isNew() {
        return true;
    }

    public Post() {
    }

    public Post(Integer id) {
        this.id = id;
    }

    public Post(String uuid) {
        this.uuid = uuid;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

 

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

 

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return getClass().hashCode();
    }
}
