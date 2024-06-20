package com.shop.product.services;

import com.shop.product.dto.ProductDto;
import com.shop.product.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    Boolean createProduct(List<MultipartFile> photo, ProductDto productDto);

    Optional<List<Product>> Products();

    Optional<Product> ProductById(String id);

    Boolean deleteById(String id);
}
