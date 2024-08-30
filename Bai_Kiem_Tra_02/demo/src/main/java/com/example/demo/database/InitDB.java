package com.example.demo.database;

import com.example.demo.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component

public class InitDB implements CommandLineRunner {

    @Autowired
    private IFileReader iFileReader;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Đọc dữ liệu từ file");
        ProductDb.products = iFileReader.readFile("products.json");
        System.out.println("Tổng số product = " + ProductDb.products.size());
    }
}