package com.shop.amazonS3.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final S3Client s3Client;
    private final FileValidator fileValidator;

    @Override
    public String uploadFile(MultipartFile file, String bucketName) throws IOException {
        fileValidator.validateFile(file);

        String fileExtension = fileValidator.getFileExtension(file.getOriginalFilename());
        String filePath = UUID.randomUUID() + "." + fileExtension;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        s3Client.uploadFile(bucketName, filePath, file.getInputStream(), metadata);
        return s3Client.getPublicUrl(bucketName, filePath);
    }

    @Override
    public String deleteFileByUrl(List<String> urls) throws IOException {
        for (String url : urls) {
            try {
                URL fileUrl = new URL(url);
                String bucketName = extractBucketName(fileUrl);
                String filePath = extractFilePath(fileUrl);
                s3Client.deleteFile(bucketName, filePath);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Invalid URL provided: " + url);
            } catch (AmazonServiceException e) {
                throw new IOException("Failed to delete file from S3: " + e.getMessage(), e);
            }
        }
        return "Files deleted successfully.";
    }

    private String extractBucketName(URL fileUrl) {
        String host = fileUrl.getHost();
        return host.split("\\.")[0];
    }

    private String extractFilePath(URL fileUrl) {
        return fileUrl.getPath().substring(1);
    }
}
