package com.example.demoapp.database;

import com.example.demoapp.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDB implements CommandLineRunner {

    @Autowired
    private IFileReader iFileReader;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Doc du lieu tu file");

        BookDB.books = iFileReader.readFile("books.csv");

        System.out.println("Tong so book = " + BookDB.books.size());
    }
}
