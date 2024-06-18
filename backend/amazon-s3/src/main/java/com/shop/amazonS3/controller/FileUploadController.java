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
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
            String publicUrl = fileUploadService.uploadFile(file, bucketName);
            return ResponseEntity.ok(publicUrl);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while uploading the file.");
        }
    }
}
