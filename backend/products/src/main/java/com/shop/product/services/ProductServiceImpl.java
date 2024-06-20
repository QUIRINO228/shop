package com.shop.product.services;

import com.shop.product.dto.ProductDto;
import com.shop.product.model.Product;
import com.shop.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FileService fileService;

    @Override
    public Boolean createProduct(List<MultipartFile> photos, ProductDto productDto) {
        validatePhotos(photos);

        Product product = buildProductFromDto(photos, productDto);
        productRepository.save(product);
        log.info("Product created: {}", product.getId());
        return true;
    }

    @Override
    public Optional<List<Product>> Products() {
        return Optional.of(productRepository.findAll());
    }

    @Override
    public Optional<Product> ProductById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Boolean deleteById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return false;
        }

        Product product = optionalProduct.get();
        List<String> photoUrls = gatherPhotoUrls(product);

        boolean photosDeleted = fileService.deleteFiles(photoUrls);
        if (!photosDeleted) {
            return false;
        }

        productRepository.deleteById(id);
        log.info("Product deleted: {}", id);
        return true;
    }

    private Product buildProductFromDto(List<MultipartFile> photos, ProductDto productDto) {
        String previewPhotoUrl = fileService.uploadPhoto(photos.get(0));
        List<String> listOfPhotos = buildListOfPhotosUrls(photos);

        return Product.builder()
                .title(productDto.getTitle())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .previewPhoto(previewPhotoUrl)
                .listOfPhotos(listOfPhotos)
                .build();
    }

    private List<String> gatherPhotoUrls(Product product) {
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(product.getPreviewPhoto());
        photoUrls.addAll(product.getListOfPhotos());
        return photoUrls;
    }

    private List<String> buildListOfPhotosUrls(List<MultipartFile> photos) {
        if (photos.size() > 1) {
            return photos.subList(1, photos.size()).stream()
                    .map(fileService::uploadPhoto)
                    .toList();
        }
        return List.of();
    }

    private void validatePhotos(List<MultipartFile> photos) {
        if (photos == null || photos.isEmpty()) {
            throw new IllegalArgumentException("No photos provided for the product.");
        }
    }
}
