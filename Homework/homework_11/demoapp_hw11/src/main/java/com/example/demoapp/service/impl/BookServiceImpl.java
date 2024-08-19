package com.example.demoapp.service.impl;

import com.example.demoapp.dao.BookDao;
import com.example.demoapp.model.Book;
import com.example.demoapp.service.Bookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements Bookservice {
    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(int id) {
        return bookDao.findById(id);
    }
}
