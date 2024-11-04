package com.example.managelibraryrestful.rest;

import com.example.managelibraryrestful.exceptionhandling.exception.ObjectNotFoundException;
import com.example.managelibraryrestful.model.request.BookRequest;
import com.example.managelibraryrestful.model.response.BookResponse;
import com.example.managelibraryrestful.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/books")
public class BookResource {

    BookService bookService;

    @GetMapping
    public List<BookResponse> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public BookResponse getBookDetails(@PathVariable("id") Long idBook) throws ObjectNotFoundException {
        return bookService.getBookDetails(idBook);
    }

    @PostMapping
    public BookResponse creatBook(@RequestBody @Valid BookRequest request) {
        return bookService.creatBook(request);
    }

    @PutMapping("{id}")
    public BookResponse updateBook(@PathVariable("id") Long idBook, @RequestBody BookRequest request) throws ObjectNotFoundException {
        return bookService.updateBook(idBook, request);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable("id") Long idBook) {
        bookService.deleteBook(idBook);
    }

}
