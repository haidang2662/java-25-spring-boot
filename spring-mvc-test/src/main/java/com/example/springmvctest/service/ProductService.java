package com.example.springmvctest.service;

import com.example.springmvctest.entity.Product;
import com.example.springmvctest.model.request.SearchProductRequest;
import com.example.springmvctest.model.response.PageResponse;
import com.example.springmvctest.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    ProductRepository productRepository;

    public PageResponse<Product> searchProduct(SearchProductRequest request) {
        return productRepository.searchProduct(request);
    }
}
