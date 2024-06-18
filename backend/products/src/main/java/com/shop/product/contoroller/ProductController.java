package com.shop.product.contoroller;

import com.shop.product.dto.ProductDto;
import com.shop.product.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create-product")
    public ResponseEntity<Object> createProduct(@RequestParam("photos") List<MultipartFile> photos, ProductDto productDto) {
        return productService.createProduct(photos, productDto)
                ? ResponseEntity.ok().body(Map.of("message", "Created product successful"))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Created product failed"));
    }
}

