
package com.example.demoapp.service;

import com.example.demoapp.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book findBookById(int id);

    List<Book> findBooksByTitle(String keyword);

    List<Book> findBooksBeetweenYears(int starYear , int endYear);

    List<Book> sortBookByYearDesc();
}
