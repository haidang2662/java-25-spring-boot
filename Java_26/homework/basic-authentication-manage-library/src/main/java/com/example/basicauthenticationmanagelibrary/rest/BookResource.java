package com.example.basicauthenticationmanagelibrary.rest;

import com.example.basicauthenticationmanagelibrary.model.reponse.BookResponse;
import com.example.basicauthenticationmanagelibrary.model.request.BookRequest;
import com.example.basicauthenticationmanagelibrary.service.BookService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class BookResource {

    BookService bookService;

    @GetMapping
    public List<BookResponse> getAll(){
        return bookService.getAll();
    }

    @PostMapping
    public BookResponse creatBook(@RequestBody BookRequest request){
        return bookService.creatBook(request);
    }

    @PutMapping("{id}")
    public BookResponse updateBook(@RequestBody @Valid BookRequest request , @PathVariable Long id) throws ClassNotFoundException {
        return bookService.updateBook(request , id);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }

    @GetMapping("{id}/activation")
    public BookResponse changeBookActivation(@PathVariable Long id) throws ClassNotFoundException {
        return bookService.changeBookActivation(id);
    }
}
