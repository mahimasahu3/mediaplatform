package com.example.mediaplatform.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "media_assets")
public class MediaAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private String type; // video/audio

    @Column(nullable=false)
    private String fileUrl;

    @Column(nullable=false)
    private Instant createdAt;

    public MediaAsset() {}

    public MediaAsset(Long id, String title, String type, String fileUrl, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.fileUrl = fileUrl;
        this.createdAt = createdAt;
    }
    
    public MediaAsset(String title, String type, String fileUrl, Instant createdAt) {
        this.title = title;
        this.type = type;
        this.fileUrl = fileUrl;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
