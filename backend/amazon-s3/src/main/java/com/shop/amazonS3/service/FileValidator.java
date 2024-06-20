package com.shop.amazonS3.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
public class FileValidator {
    private static final List<String> VALID_IMAGE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp", "webp", "svg");
    private static final long MAX_FILE_SIZE_BYTES = 10 * 1024 * 1024;

    public void validateFile(MultipartFile file) {
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
    }

    private boolean isValidImageExtension(String extension) {
        return VALID_IMAGE_EXTENSIONS.contains(extension.toLowerCase());
    }

    public String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}