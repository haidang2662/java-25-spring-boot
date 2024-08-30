package com.example.demoapp;


import com.example.demoapp.model.Book;
import com.example.demoapp.utils.CsvFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class DemoappApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoappApplication.class, args);

//		CsvFileReader reader = new CsvFileReader();
//		List<Book> books = reader.readFile("C:\\Users\\DELL\\OneDrive\\Desktop\\techmaster-spring-boot\\java-25-spring-boot\\Homework\\homework_11\\demoapp_hw11\\books.csv");
//
//		books.forEach(System.out::println);
    }

}
