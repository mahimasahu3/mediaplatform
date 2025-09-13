package com.example.mediaplatform.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "media_view_logs")
public class MediaViewLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="media_id", nullable=false)
    private MediaAsset media;

    @Column(nullable=false)
    private String viewedByIp;

    @Column(nullable=false)
    private Instant timestamp;

    public MediaViewLog() {}

    public MediaViewLog(Long id, MediaAsset media, String viewedByIp, Instant timestamp) {
        this.id = id;
        this.media = media;
        this.viewedByIp = viewedByIp;
        this.timestamp = timestamp;
    }
    
    public MediaViewLog(MediaAsset media, String viewedByIp, Instant timestamp) {
        this.media = media;
        this.viewedByIp = viewedByIp;
        this.timestamp = timestamp;
    }


    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public MediaAsset getMedia() { return media; }
    public void setMedia(MediaAsset media) { this.media = media; }

    public String getViewedByIp() { return viewedByIp; }
    public void setViewedByIp(String viewedByIp) { this.viewedByIp = viewedByIp; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
