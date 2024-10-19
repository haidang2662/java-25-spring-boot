package com.example.managelibraryrestful.controller;

import com.example.managelibraryrestful.model.response.BookResponse;
import com.example.managelibraryrestful.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    BookService bookService;

    @GetMapping
    public String listBook(Model model){
        List<BookResponse> bookResponses = bookService.getBooks();
        model.addAttribute("books" , bookResponses);
        return "/book/books";
    }

}
