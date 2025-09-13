package com.example.mediaplatform.service;

import com.example.mediaplatform.entity.MediaAsset;
import com.example.mediaplatform.repository.MediaAssetRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

@Service
public class MediaService {

    private final MediaAssetRepository mediaAssetRepository;

    @Value("${app.file.upload-dir}")
    private String uploadDir;

    public MediaService(MediaAssetRepository mediaAssetRepository) {
        this.mediaAssetRepository = mediaAssetRepository;
    }

    public MediaAsset uploadMedia(String title, String type, MultipartFile file) throws IOException {
        File dir = new File(uploadDir);
        if(!dir.exists()) dir.mkdirs();

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File destination = new File(dir, filename);
        file.transferTo(destination);

        MediaAsset media = new MediaAsset(title, type, "/uploads/" + filename, Instant.now());
        return mediaAssetRepository.save(media);
    }

    public MediaAsset getMedia(Long id) throws Exception {
        return mediaAssetRepository.findById(id).orElseThrow(() -> new Exception("Media not found"));
    }
}
