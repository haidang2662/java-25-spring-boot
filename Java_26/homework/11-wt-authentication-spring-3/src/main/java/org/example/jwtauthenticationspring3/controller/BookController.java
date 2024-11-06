package org.example.jwtauthenticationspring3.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.jwtauthenticationspring3.exception.ObjectNotFoundException;
import org.example.jwtauthenticationspring3.model.request.BookRequest;
import org.example.jwtauthenticationspring3.model.response.BookResponse;
import org.example.jwtauthenticationspring3.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable Long id , @Valid @RequestBody BookRequest request) throws ObjectNotFoundException {
        return bookService.updateBook(id , request);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}/changestatus")
    public BookResponse changeStatusBook(@PathVariable Long id) throws ObjectNotFoundException {
        return bookService.changeStatusBook(id);
    }
}
