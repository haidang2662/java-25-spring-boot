package com.example.demo.utils;

import com.example.demo.model.Product;

import java.util.List;

public interface IFileReader {

    List<Product> readFile(String path);

}
