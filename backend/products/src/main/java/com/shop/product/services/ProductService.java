package com.shop.product.services;

import com.shop.product.dto.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface ProductService {
    Boolean createProduct(List<MultipartFile> photo, ProductDto productDto);
}
