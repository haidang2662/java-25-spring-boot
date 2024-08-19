package com.example.demoapp.dao.impl;

import com.example.demoapp.dao.BookDao;
import com.example.demoapp.database.BookDB;
import com.example.demoapp.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static com.example.demoapp.database.BookDB.books;

@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public Book findById(int id) {
        for (Book book : BookDB.books){
            if(book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
