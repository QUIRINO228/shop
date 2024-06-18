package com.shop.product.services;

import com.shop.product.client.AmazonS3Client;
import com.shop.product.dto.ProductDto;
import com.shop.product.model.Product;
import com.shop.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final AmazonS3Client amazonS3Client;
    private final ProductRepository productRepository;

    @Override
    public Boolean createProduct(List<MultipartFile> photos, ProductDto productDto) {
        if (photos == null || photos.isEmpty()) {
            throw new IllegalArgumentException("No photos provided for the product.");
        }

        Product product = buildProductFromDto(photos, productDto);
        productRepository.save(product);
        return true;
    }

    private Product buildProductFromDto(List<MultipartFile> photos, ProductDto productDto) {
        String previewPhotoUrl = uploadPhotoAndGetUrl(photos.get(0));
        List<String> listOfPhotos = buildListOfPhotosUrls(photos);

        return Product.builder()
                .title(productDto.getTitle())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .previewPhoto(previewPhotoUrl)
                .listOfPhotos(listOfPhotos)
                .build();
    }

    private String uploadPhotoAndGetUrl(MultipartFile photo) {
        if (photo == null) {
            throw new IllegalArgumentException("Photo cannot be null.");
        }
        return amazonS3Client.uploadPhoto(photo);
    }

    private List<String> buildListOfPhotosUrls(List<MultipartFile> photos) {
        if (photos.size() > 1) {
            return IntStream.range(1, photos.size())
                    .mapToObj(i -> uploadPhotoAndGetUrl(photos.get(i)))
                    .collect(Collectors.toList());
        }
        return List.of();
    }
}
