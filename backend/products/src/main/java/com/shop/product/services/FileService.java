package com.shop.product.services;

import com.shop.product.components.AmazonS3Client;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    private final AmazonS3Client amazonS3Client;

    public FileService(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public String uploadPhoto(MultipartFile photo) {
        return amazonS3Client.uploadPhoto(photo);
    }

    public boolean deleteFiles(List<String> urls) {
        ResponseEntity<String> response = amazonS3Client.deleteFileByUrl(urls);
        return response.getStatusCode().is2xxSuccessful();
    }
}
