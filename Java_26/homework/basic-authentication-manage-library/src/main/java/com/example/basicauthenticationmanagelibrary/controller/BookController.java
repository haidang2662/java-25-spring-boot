package com.example.basicauthenticationmanagelibrary.controller;

import com.example.basicauthenticationmanagelibrary.model.reponse.BookResponse;
import com.example.basicauthenticationmanagelibrary.model.request.BookRequest;
import com.example.basicauthenticationmanagelibrary.service.BookService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/books")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class BookController {

    BookService bookService;

    @GetMapping
    public List<BookResponse> getAll(){
        return bookService.getAll();
    }

    @PostMapping
    public BookResponse creatBook(@RequestBody BookRequest request){
        return bookService.creatBook(request);
    }
}
