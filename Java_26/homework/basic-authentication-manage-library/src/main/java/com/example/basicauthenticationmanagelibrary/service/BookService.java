package com.example.basicauthenticationmanagelibrary.service;

import com.example.basicauthenticationmanagelibrary.entity.Book;
import com.example.basicauthenticationmanagelibrary.model.reponse.BookResponse;
import com.example.basicauthenticationmanagelibrary.model.request.BookRequest;
import com.example.basicauthenticationmanagelibrary.reposiotry.BookRepository;
import com.example.basicauthenticationmanagelibrary.statics.BookStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService {

    BookRepository bookRepository;

    ObjectMapper objectMapper;

    public List<BookResponse> getAll() {
        List<Book> books = bookRepository.findAll();
        if(!CollectionUtils.isEmpty(books)){
            return books.stream().map(b -> objectMapper.convertValue(b , BookResponse.class)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public BookResponse creatBook(BookRequest request) {
        Book book = objectMapper.convertValue(request , Book.class);
        book.setStatus(BookStatus.INACTIVE);
        bookRepository.save(book);
        return objectMapper.convertValue(book , BookResponse.class);
    }

    public BookResponse updateBook(BookRequest request, Long id) throws ClassNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(ClassNotFoundException::new);
        book.setName(request.getName());
        book.setAuthor(request.getAuthor());
        book.setPrice(request.getPrice());
        book.setPublisher(request.getPublisher());
        bookRepository.save(book);
        return objectMapper.convertValue(book , BookResponse.class);
    }


    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public BookResponse changeBookActivation(Long id) throws ClassNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(ClassNotFoundException::new);
        if(book.getStatus() == BookStatus.INACTIVE){
            book.setStatus(BookStatus.ACTIVE);
        } else {
            book.setStatus(BookStatus.INACTIVE);
        }
        bookRepository.save(book);
        return objectMapper.convertValue(book , BookResponse.class);
    }
}
