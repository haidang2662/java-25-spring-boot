package com.example.demoapp.untils;

import com.example.demoapp.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // sd thu vien Apache POI
public class ExcelFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String path) {
        return List.of();
    }
}
