package com.example.basicauthenticationmanagelibrary.rest;

import com.example.basicauthenticationmanagelibrary.entity.BookBorrow;
import com.example.basicauthenticationmanagelibrary.model.reponse.BookBorrowResponse;
import com.example.basicauthenticationmanagelibrary.model.request.BookBorrowRequest;
import com.example.basicauthenticationmanagelibrary.service.BookBorrowService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrows")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BookBorrowResource {

    BookBorrowService bookBorrowService;

    @GetMapping
    public List<BookBorrowResponse> getAll(){
        return bookBorrowService.getAll();
    }

    @PostMapping
    public BookBorrowResponse createBorrow(@RequestBody @Valid BookBorrowRequest request){
        return bookBorrowService.createBorrow(request);
    }
}
