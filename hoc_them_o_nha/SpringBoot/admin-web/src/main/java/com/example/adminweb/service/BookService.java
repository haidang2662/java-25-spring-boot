package com.example.adminweb.service;

import com.example.adminweb.constant.BookStatus;
import com.example.adminweb.entity.Book;
import com.example.adminweb.exceptionHandling.ObjectNotFoundException;
import com.example.adminweb.model.request.BookCreationRequest;
import com.example.adminweb.model.request.UpdateBookRequest;
import com.example.adminweb.model.response.BookResponse;
import com.example.adminweb.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    BookRepository bookRepository;

    ObjectMapper objectMapper;

    public void createBook(BookCreationRequest bookCreationRequest) {
        Book book = objectMapper.convertValue(bookCreationRequest, Book.class);
        bookRepository.save(book);
    }

    public Page<BookResponse> findAllBooks(int page, int pageSize) {
        // 1 - tìm trong DB
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("id").descending());
        Page<Book> bookPage = bookRepository.findAll(pageable);

        // 2 - convert từ entity sang response
        List<Book> books = bookPage.getContent();

        // 2.1 - code chay thông thường
//        List<BookResponse> bookResponses = new ArrayList<>();
//        for (int i = 0; i < books.size(); i++) {
//            Book bookTemp = books.get(i);
//
//            BookResponse response = new BookResponse();
//            response.setId(bookTemp.getId());
//            response.setName(bookTemp.getName());
//            response.setAuthor(bookTemp.getAuthor());
//            response.setPublishedAt(bookTemp.getPublishedAt());
//            response.setPublisher(bookTemp.getPublisher());
//            response.setPrice(bookTemp.getPrice());
//            response.setRating(bookTemp.getRating());
//            response.setTotalPages(bookTemp.getTotalPages());
//            response.setStatus(bookTemp.getStatus());
//
//            bookResponses.add(response);
//        }

        // 2.2 - dùng builder
//        List<BookResponse> bookResponses = new ArrayList<>();
//        for (int i = 0; i < books.size(); i++) {
//            Book bookTemp = books.get(i);
//
//            BookResponse response = BookResponse.builder()
//                    .id(bookTemp.getId())
//                    .name(bookTemp.getName())
//                    .author(bookTemp.getAuthor())
//                    .publishedAt(bookTemp.getPublishedAt())
//                    .publisher(bookTemp.getPublisher())
//                    .price(bookTemp.getPrice())
//                    .rating(bookTemp.getRating())
//                    .totalPages(bookTemp.getTotalPages())
//                    .status(bookTemp.getStatus())
//                    .build();
//
//            bookResponses.add(response);
//        }

        // 2.3- dùng object mapper
//        List<BookResponse> bookResponses = new ArrayList<>();
//        for (int i = 0; i < books.size(); i++) {
//            Book bookTemp = books.get(i);
//
//            BookResponse response = objectMapper.convertValue(bookTemp, BookResponse.class);
//
//            bookResponses.add(response);
//        }

        // 2.4 - dùng object mapper kết hợp với stream của java 8
        List<BookResponse> bookResponses = books
                .stream()
                .map(bookTemp -> objectMapper.convertValue(bookTemp, BookResponse.class))
                .toList();

        // 3 - trả về kết quả
        return new PageImpl<>(bookResponses, pageable, bookPage.getTotalElements());
    }

    public void deleteByIdBook(long id) {
        bookRepository.deleteById(id);
    }

    //Todo : Doc ky de hieu sau them doan code nay lien quan den ca ObjectNotFoundException , java 8 ,
    public UpdateBookRequest findBookById(long id) {
        Optional<Book> bookOptional = bookRepository.findById(id); // xem ky lai doan nay trong java 8 ly thuyet cua thay truong
        if (bookOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay sach mang id : " + id);
        }
        Book book = bookOptional.get();
        return objectMapper.convertValue(book, UpdateBookRequest.class);
    }

    public void updateBook(UpdateBookRequest updateBook) {
        Optional<Book> bookOptional = bookRepository.findById(updateBook.getId());
        if (bookOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay book mang Id : " + updateBook.getId());
        }

        Book book = bookOptional.get();
        BookStatus currentStatus = book.getStatus();

        // cách 1 - làm bằng set thủ công
//        book.setName(updateBook.getName());
//        book.setPublishedAt(updateBook.getPublishedAt());
//        book.setPrice(updateBook.getPrice());
//        book.setRating(updateBook.getRating());
//        book.setAuthor(updateBook.getAuthor());
//        book.setPublisher(updateBook.getPublisher());

        // cách 2 - object mapper
        book = objectMapper.convertValue(updateBook, Book.class);
        book.setStatus(currentStatus);
        bookRepository.save(book);
    }

    public void changeBookActivation(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy book mang Id: " + id);
        }

        Book book = bookOptional.get();
        book.setStatus(book.getStatus() == BookStatus.ACTIVE ? BookStatus.INACTIVE : BookStatus.ACTIVE);
        bookRepository.save(book);
    }

    public List<BookResponse> findActiveBook() {
        List<Book> books = bookRepository.findByStatus(BookStatus.ACTIVE);
        return books
                .stream()
                .map(bookTemp -> objectMapper.convertValue(bookTemp, BookResponse.class))
                .toList();
    }

    public Book findById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        } else {
            throw new ObjectNotFoundException("Không tìm thấy sách với ID: " + id);
        }
    }
}
