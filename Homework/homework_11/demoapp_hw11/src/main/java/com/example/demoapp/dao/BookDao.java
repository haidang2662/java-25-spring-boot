package com.example.demoapp.dao;

import com.example.demoapp.model.Book;

public interface BookDao {
    Book findById(int id);
}
