package com.shop.amazonS3.controller;

import com.shop.amazonS3.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/amazons3")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${amazons3.bucket_name}")
    private String bucketName;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String publicUrl = fileService.uploadFile(file, bucketName);
            return ResponseEntity.ok(publicUrl);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while uploading the file.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFileByUrl(@RequestParam("url") List<String> urls) {
        try {
            String response = fileService.deleteFileByUrl(urls);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("An error occurred while deleting the file.");
        }
    }
}
