package com.shop.amazonS3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private final AmazonS3 amazonS3;

    private static final String[] VALID_IMAGE_EXTENSIONS = {"jpg", "jpeg", "png", "gif", "bmp", "webp", "svg"};
    private static final long MAX_FILE_SIZE_BYTES = 10 * 1024 * 1024;

    public void uploadFile(String bucketName, String filePath, InputStream inputStream, ObjectMetadata metadata) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filePath, inputStream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);
    }

    public String uploadFile(MultipartFile file, String bucketName) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty.");
        }
        if (file.getSize() > MAX_FILE_SIZE_BYTES) {
            throw new IllegalArgumentException("File size exceeds the maximum allowed size (10 MB).");
        }
        String fileExtension = getFileExtension(file.getOriginalFilename());
        if (!isValidImageExtension(fileExtension)) {
            throw new IllegalArgumentException("Invalid file extension. Only image files are allowed.");
        }

        String filePath = UUID.randomUUID() + "." + fileExtension;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        uploadFile(bucketName, filePath, file.getInputStream(), metadata);
        return getPublicUrl(bucketName, filePath);
    }

    private boolean isValidImageExtension(String extension) {
        for (String validExtension : VALID_IMAGE_EXTENSIONS) {
            if (validExtension.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    public String getPublicUrl(String bucketName, String filePath) {
        return amazonS3.getUrl(bucketName, filePath).toString();
    }
}
