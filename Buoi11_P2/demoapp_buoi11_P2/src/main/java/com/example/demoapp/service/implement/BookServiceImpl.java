package com.example.demoapp.service.implement;

import com.example.demoapp.dao.BookDao;
import com.example.demoapp.model.Book;
import com.example.demoapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service

public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public Book findBookById(int id) {

        return bookDao.findById(id);
    }

    @Override
    public List<Book> findBooksByTitle(String keyword) {
        return bookDao.findByTitleContainIgnoreCase(keyword);
    }

    @Override
    public List<Book> findBooksBeetweenYears(int starYear, int endYear) {
        List<Book> books = bookDao.findAll();
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() >= starYear && book.getYear() <= endYear) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> sortBookByYearDesc() {
        List<Book> books = bookDao.findAll();
        List<Book> result = new ArrayList<>();
        books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getYear() - o1.getYear();
            }
        });
        return null;
    }
}
