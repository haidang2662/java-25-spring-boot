package com.example.demoapp.dao.Impl;

import com.example.demoapp.dao.BookDao;
import com.example.demoapp.database.BookDB;
import com.example.demoapp.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.example.demoapp.database.BookDB.books;

@Repository

public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> findAll() {
        return BookDB.books;
    }
    @Override
    public Book findById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book; // 200
            }
        }
        return null;
    }

    @Override
    public List<Book> findByTitleContainIgnoreCase(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if(book.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                result.add(book);
            }
        }
        return result;
    }
}
