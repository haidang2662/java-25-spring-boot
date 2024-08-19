package com.example.demoapp.dao;

import com.example.demoapp.model.Book;

import java.util.List;

public interface BookDao {
    public  List<Book> findAll();

    Book findById(int id);

    List<Book> findByTitleContainIgnoreCase(String keyword);
}
