package com.example.basicauthenticationmanagelibrary.service;

import com.example.basicauthenticationmanagelibrary.entity.BookBorrow;
import com.example.basicauthenticationmanagelibrary.model.reponse.BookBorrowResponse;
import com.example.basicauthenticationmanagelibrary.model.request.BookBorrowRequest;
import com.example.basicauthenticationmanagelibrary.reposiotry.BookBorrowRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class BookBorrowService {

    BookBorrowRepository bookBorrowRepository;

    ObjectMapper objectMapper;

    public List<BookBorrowResponse> getAll() {
        List<BookBorrow> borrows = bookBorrowRepository.findAll();
        if(!CollectionUtils.isEmpty(borrows)){
            return borrows.stream().map(b -> objectMapper.convertValue(b , BookBorrowResponse.class)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public BookBorrowResponse createBorrow(BookBorrowRequest request) {
        BookBorrow borrow = objectMapper.convertValue(request ,BookBorrow.class);
        borrow.setCreatedDate(LocalDate.now());
        bookBorrowRepository.save(borrow);
        return objectMapper.convertValue(borrow , BookBorrowResponse.class);
    }
}
