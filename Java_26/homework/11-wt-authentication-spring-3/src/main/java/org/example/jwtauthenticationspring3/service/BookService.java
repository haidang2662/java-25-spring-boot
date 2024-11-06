package org.example.jwtauthenticationspring3.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.example.jwtauthenticationspring3.entity.Book;
import org.example.jwtauthenticationspring3.exception.ObjectNotFoundException;
import org.example.jwtauthenticationspring3.model.request.BookRequest;
import org.example.jwtauthenticationspring3.model.response.BookResponse;
import org.example.jwtauthenticationspring3.repository.BookRepository;
import org.example.jwtauthenticationspring3.statics.BookStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
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


    public BookResponse updateBook(Long id, BookRequest request) throws ObjectNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isEmpty()){
            throw new ObjectNotFoundException("Khong tim thay sach co id la : " +id);
        }
        Book book = bookOptional.get();
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

    public BookResponse changeStatusBook(Long id) throws ObjectNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Khong tim thay sach co id la " +id));
        if(book.getStatus() == BookStatus.ACTIVE){
            book.setStatus(BookStatus.INACTIVE);
        } else {
            book.setStatus(BookStatus.ACTIVE);
        }
        return objectMapper.convertValue(book , BookResponse.class);
    }
}
