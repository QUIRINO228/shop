package com.shop.product.contoroller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.shop.product.model.Product;
import com.shop.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PostQueryController implements GraphQLQueryResolver {

    private final ProductService productService;

    @Autowired
    public PostQueryController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public Optional<List<Product>> Products() {
        return productService.Products();
    }

    @QueryMapping
    public Optional<Product> ProductById(@Argument("id") String id) {
        return productService.ProductById(id);
    }

}
