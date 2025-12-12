package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalUrl;
    private String shortCode;

    private Long clicks = 0L;
    private LocalDateTime lastAccessed;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime expiryAt;

    public Url() {}


    public Url(Long id, String originalUrl, String shortCode, Long clicks, LocalDateTime lastAccessed, LocalDateTime createdAt, LocalDateTime expiryAt) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.clicks = clicks;
        this.lastAccessed = lastAccessed;
        this.createdAt = createdAt;
        this.expiryAt = expiryAt;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    public LocalDateTime getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(LocalDateTime lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiryAt() {
        return expiryAt;
    }

    public void setExpiryAt(LocalDateTime expiryAt) {
        this.expiryAt = expiryAt;
    }
}
