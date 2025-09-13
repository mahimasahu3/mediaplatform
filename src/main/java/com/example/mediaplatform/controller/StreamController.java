package com.example.mediaplatform.controller;

import com.example.mediaplatform.entity.MediaAsset;
import com.example.mediaplatform.service.MediaService;
import com.example.mediaplatform.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Base64;

@RestController
@RequestMapping("/media")
public class StreamController {

    private final MediaService mediaService;
    private final JwtService jwtService;

    @Value("${app.jwt.stream-expiration-ms}")
    private long streamExpirationMs;

    public StreamController(MediaService mediaService, JwtService jwtService) {
        this.mediaService = mediaService;
        this.jwtService = jwtService;
    }

    @GetMapping("/{id}/stream-url")
    public ResponseEntity<String> getStreamUrl(@PathVariable Long id) throws Exception {
        MediaAsset media = mediaService.getMedia(id);
        long expiry = Instant.now().toEpochMilli() + streamExpirationMs;
        String tokenPayload = media.getId() + ":" + expiry;
        String token = Base64.getEncoder().encodeToString(tokenPayload.getBytes());
        String url = media.getFileUrl() + "?token=" + token;
        return ResponseEntity.ok(url);
    }
}
