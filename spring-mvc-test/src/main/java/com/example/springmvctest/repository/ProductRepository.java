package com.example.springmvctest.repository;

import com.example.springmvctest.entity.Product;
import com.example.springmvctest.model.request.SearchProductRequest;
import com.example.springmvctest.model.response.PageResponse;
import com.example.springmvctest.model.response.PageResponseImpl;
import com.example.springmvctest.util.DataUtil;
import com.example.springmvctest.util.FileUtil;
import com.example.springmvctest.util.StringUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class ProductRepository {

    private final FileUtil<Product> productFileUtil = new FileUtil<>();

    public List<Product> findAll() {
        try {
            File file = new ClassPathResource("db/products.json").getFile();
            return productFileUtil.readDataFromFile(file.getAbsolutePath(), Product[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PageResponse<Product> searchProduct(SearchProductRequest request) {
        List<Product> products = findAll();

        if (!StringUtil.isNullOrEmpty(request.getName())) {
            products = products.stream()
                    .filter(b -> b.getName().toLowerCase().contains(request.getName().trim().toLowerCase()))
                    .toList();
        }

        if (!StringUtil.isNullOrEmpty(request.getDescription())) {
            products = products.stream()
                    .filter(b -> b.getDescription().toLowerCase().contains(request.getDescription().trim().toLowerCase()))
                    .toList();
        }

        if (!DataUtil.isNullOrEmpty(request.getMinPrice())) {
            products = products.stream()
                    .filter(b -> b.getPrice() >= request.getMinPrice())
                    .toList();
        }

        if (!DataUtil.isNullOrEmpty(request.getMaxPrice())) {
            products = products.stream()
                    .filter(b -> b.getPrice() <= request.getMaxPrice())
                    .toList();
        }

        return PageResponseImpl.<Product>builder()
                .currentPage(request.getPageNumber())
                .pageSize(request.getPageSize())
                .data(products)
                .build();
    }
}
