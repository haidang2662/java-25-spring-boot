package com.example.demo.dao;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductDao {

    public List<Product> findAll();
    Product findById(int id);

    List<Product> findByNameContainIgnoreCase(String keyword);
}
