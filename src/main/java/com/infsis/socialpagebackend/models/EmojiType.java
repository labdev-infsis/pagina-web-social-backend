package com.infsis.socialpagebackend.models;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "emoji_type")
public class EmojiType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false, nullable = false, unique = true, length = 36)
    private String uuid;

    @Column(nullable = false, length = 15)
    private String emoji_name;

    @Column(nullable = false, length = 5)
    private String emoji_code;

    public EmojiType() {
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

    public String getEmoji_name() {
        return emoji_name;
    }

    public void setEmoji_name(String emoji_name) {
        this.emoji_name = emoji_name;
    }

    public String getEmoji_code() {
        return emoji_code;
    }

    public void setEmoji_code(String emoji_code) {
        this.emoji_code = emoji_code;
    }

    @PrePersist
    public void initializeUuid() {
        this.setUuid(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmojiType emojiType = (EmojiType) o;
        return Objects.equals(id, emojiType.id) && Objects.equals(uuid, emojiType.uuid) && Objects.equals(emoji_name, emojiType.emoji_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, emoji_name);
    }
}
