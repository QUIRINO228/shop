package com.shop.amazonS3.controller;

import com.shop.amazonS3.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/amazons3")
@Slf4j
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @Value("${amazons3.bucket_name}")
    private String bucketName;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            log.info("Received request to upload file: {}", file.getOriginalFilename());
            String publicUrl = fileUploadService.uploadFile(file, bucketName);
            log.info("File uploaded successfully. Public URL: {}", publicUrl);
            return ResponseEntity.ok(publicUrl);
        } catch (IllegalArgumentException e) {
            log.error("Error occurred while uploading file: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred while uploading the file.", e);
            return ResponseEntity.status(500).body("An error occurred while uploading the file.");
        }
    }
}
