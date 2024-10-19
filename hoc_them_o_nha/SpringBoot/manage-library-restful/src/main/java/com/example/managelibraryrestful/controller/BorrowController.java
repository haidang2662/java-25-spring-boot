package com.example.managelibraryrestful.controller;

import com.example.managelibraryrestful.model.request.BorrowRequest;
import com.example.managelibraryrestful.model.response.BookResponse;
import com.example.managelibraryrestful.model.response.BorrowResponse;
import com.example.managelibraryrestful.model.response.ReaderResponse;
import com.example.managelibraryrestful.service.BookService;
import com.example.managelibraryrestful.service.BorrowService;
import com.example.managelibraryrestful.service.ReaderService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/borrows")
public class BorrowController {

    BorrowService borrowService;
    BookService bookService;
    ReaderService readerService;

    @GetMapping
    public String getListBorrow(Model model){

        model.addAttribute("borrowRequest" , new BorrowRequest());

        List<BorrowResponse> borrowResponses = borrowService.getBorrows();
        model.addAttribute("borrows" , borrowResponses);
        List<BookResponse> bookResponses = bookService.getBooks();
        model.addAttribute("books" , bookResponses );
        List<ReaderResponse> readerResponses = readerService.getReaders();
        model.addAttribute("readers" , readerResponses);
        return "/borrow/borrows";
    }
}
