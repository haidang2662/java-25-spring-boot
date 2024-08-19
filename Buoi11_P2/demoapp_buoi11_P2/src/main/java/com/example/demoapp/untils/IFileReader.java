package com.example.demoapp.untils;

import com.example.demoapp.model.Book;

import java.util.List;

public interface IFileReader {
    List<Book> readFile(String path);
}
