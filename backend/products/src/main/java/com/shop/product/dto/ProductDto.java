package com.shop.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String id;
    private String title;
    private String description;
    private String previewPhoto;
    private List<String> listOfPhotos = new ArrayList<>();
    private BigDecimal price;
}
