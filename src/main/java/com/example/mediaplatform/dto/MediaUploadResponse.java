package com.example.mediaplatform.dto;

public class MediaUploadResponse {
    private Long id;
    private String title;
    private String type;
    private String fileUrl;

    public MediaUploadResponse() {}

    public MediaUploadResponse(Long id, String title, String type, String fileUrl) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.fileUrl = fileUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
}
