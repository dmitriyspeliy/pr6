package ru.effective_mobile.tinyurl.domain.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "tiny_url")
public class TinyUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "original")
    String original;
    @Column(name = "shorter")
    String shorter;
    @Column(name = "ttl")
    LocalDateTime ttl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getShorter() {
        return shorter;
    }

    public void setShorter(String shorter) {
        this.shorter = shorter;
    }

    public LocalDateTime getTtl() {
        return ttl;
    }

    public void setTtl(LocalDateTime ttl) {
        this.ttl = ttl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TinyUrl tinyUrl = (TinyUrl) o;
        return Objects.equals(id, tinyUrl.id) && Objects.equals(original, tinyUrl.original) && Objects.equals(shorter, tinyUrl.shorter) && Objects.equals(ttl, tinyUrl.ttl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, original, shorter, ttl);
    }
}
