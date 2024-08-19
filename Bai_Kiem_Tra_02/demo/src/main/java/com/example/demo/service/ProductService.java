package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {

    List<Product>getAllProduct();
    Product findProductById(int id);

    List<Product> findProductsByName(String keyword);

    List<Product> findProductsBetweenPrice(int starPrice , int endPrice);
}
