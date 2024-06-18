package com.shop.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.product.dto.ProductDto;
import com.shop.product.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private ProductService productService;
    private final ObjectMapper objectMapper;
    @PostMapping(value = "/create-product", consumes = {"multipart/form-data"})
    public ResponseEntity<Object> createProduct(@RequestParam("photos") List<MultipartFile> photos,
                                                @RequestParam("productDto") String productDtoJson) throws JsonProcessingException {
        ProductDto productDto = objectMapper.readValue(productDtoJson, ProductDto.class);
        log.info("Received productDto: {}", productDto.getTitle());
        return productService.createProduct(photos, productDto)
                ? ResponseEntity.ok().body(Map.of("message", "Created product successfully"))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Creating product failed"));
    }
}
