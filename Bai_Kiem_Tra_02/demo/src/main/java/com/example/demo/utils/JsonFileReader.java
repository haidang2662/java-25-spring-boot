package com.example.demo.utils;

import com.example.demo.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component

public class JsonFileReader implements IFileReader {
    @Override
    public List<Product> readFile(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = new ArrayList<>();
        try {
            products = objectMapper.readValue(
                    new File(path),
                    new TypeReference<List<Product>>(){}
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
