package com.shop.amazonS3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@AllArgsConstructor
@Service
public class S3Client {

    private final AmazonS3 amazonS3;


    public void uploadFile(String bucketName, String filePath, InputStream inputStream, ObjectMetadata metadata) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filePath, inputStream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);
    }

    public void deleteFile(String bucketName, String filePath) {
        amazonS3.deleteObject(bucketName, filePath);
    }

    public String getPublicUrl(String bucketName, String filePath) {
        return amazonS3.getUrl(bucketName, filePath).toString();
    }
}
