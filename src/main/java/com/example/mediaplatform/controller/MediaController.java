package com.example.mediaplatform.controller;

import com.example.mediaplatform.dto.MediaUploadResponse;
import com.example.mediaplatform.entity.MediaAsset;
import com.example.mediaplatform.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping
    public ResponseEntity<MediaUploadResponse> uploadMedia(
            @RequestParam String title,
            @RequestParam String type,
            @RequestParam MultipartFile file
    ) throws Exception {
        MediaAsset media = mediaService.uploadMedia(title, type, file);
        return ResponseEntity.ok(new MediaUploadResponse(media.getId(), media.getTitle(), media.getType(), media.getFileUrl()));
    }
}
