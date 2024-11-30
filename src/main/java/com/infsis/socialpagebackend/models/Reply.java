package com.infsis.socialpagebackend.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "replies")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer replyId;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private Date createdAt;

    @PrePersist
    public void initializeUuid() {
        this.uuid = UUID.randomUUID().toString();
    }
}