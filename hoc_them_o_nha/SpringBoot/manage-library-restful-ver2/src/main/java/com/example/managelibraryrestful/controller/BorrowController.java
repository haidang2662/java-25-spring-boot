package com.example.managelibraryrestful.controller;

import com.example.managelibraryrestful.model.response.BorrowResponse;
import com.example.managelibraryrestful.service.BorrowService;
import lombok.AllArgsConstructor;
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

    @GetMapping
    public String getListBorrow(Model model) {
        List<BorrowResponse> borrowResponses = borrowService.getBorrows();
        model.addAttribute("borrows", borrowResponses);
        return "/borrow/borrows";
    }


}
