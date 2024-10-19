package com.example.managelibraryrestful.service;

import com.example.managelibraryrestful.entity.Book;
import com.example.managelibraryrestful.exceptionhandling.exception.ObjectNotFoundException;
import com.example.managelibraryrestful.model.request.BookRequest;
import com.example.managelibraryrestful.model.response.BookResponse;
import com.example.managelibraryrestful.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    BookRepository bookRepository;
    ObjectMapper objectMapper;

    public List<BookResponse> getBooks() {
        List<Book> bookList = bookRepository.findAll();
        return bookList
                .stream()
                .map(s -> objectMapper.convertValue(s, BookResponse.class))
                .toList();
    }

    public BookResponse getBookDetails(Long idBook) throws ObjectNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(idBook);
        if (bookOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay sach co Id : " + idBook);
        }
        Book book = bookOptional.get();
        return objectMapper.convertValue(book, BookResponse.class);
    }

    public BookResponse creatBook(BookRequest request) {
        Book book = objectMapper.convertValue(request, Book.class);
        bookRepository.save(book);
        return objectMapper.convertValue(book, BookResponse.class);
    }

    public BookResponse updateBook(Long idBook, BookRequest request) throws ObjectNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(idBook);
        if (bookOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay sach co id : " + idBook);
        }

        Book book = bookOptional.get();
        book.setName(request.getName());
        book.setPrice(request.getPrice());
        book.setPublisher(request.getPublisher());
        book.setPublishedYear(request.getPublishedYear());
        bookRepository.save(book);
        return objectMapper.convertValue(book, BookResponse.class);
    }

    public void deleteBook(Long idBook) {
        bookRepository.deleteById(idBook);
    }
}
