package com.shop.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "product")
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private String previewPhoto;
    private List<String> listOfPhotos = new ArrayList<>();
    private BigDecimal price;
}
