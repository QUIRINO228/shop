package com.shop.product.components;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@FeignClient(name = "amazons3", url = "${application.config.amazons3}")
public interface AmazonS3Client {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadPhoto(@RequestPart("file") MultipartFile file);

    @DeleteMapping("/delete")
    ResponseEntity<String> deleteFileByUrl(@RequestParam("url") List<String> url);
}
