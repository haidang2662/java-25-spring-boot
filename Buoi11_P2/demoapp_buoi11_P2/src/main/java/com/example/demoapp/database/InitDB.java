package com.example.demoapp.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demoapp.untils.IFileReader;

@Component
public class InitDB implements CommandLineRunner {
    @Autowired
    private IFileReader fileReader;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Đọc dữ liệu từ file");

        BookDB.books = fileReader.readFile("books.json");

        System.out.println("Tong so book = " +BookDB.books.size());
    }
}
