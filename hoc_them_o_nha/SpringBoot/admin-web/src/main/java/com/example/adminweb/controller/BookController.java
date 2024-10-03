package com.example.adminweb.controller;

import com.example.adminweb.model.request.BookCreationRequest;
import com.example.adminweb.model.request.UpdateBookRequest;
import com.example.adminweb.model.response.BookResponse;
import com.example.adminweb.service.BookService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class BookController {

    BookService bookService;

    @GetMapping("/book-creation-form")
    public String showBookCreationForm(Model model) {
        model.addAttribute("bookRequest", new BookCreationRequest()); // Khởi tạo đối tượng User và đưa vào model
        return "book/book-creation"; // Trả về trang đăng ký
    }

    @PostMapping
    public String processBookForm(@ModelAttribute @Valid BookCreationRequest bookRequest, Errors errors, Model model) {
        // Nếu có lỗi, trả về trang đăng ký và hiển thị lỗi
        if (errors.hasErrors()) {
            model.addAttribute("bookRequest", bookRequest); // Đưa lại thông tin form vào model
            return "book-creation"; // Quay lại trang đăng ký
        }
        // Nếu không có lỗi, tiến hành đăng ký và chuyển hướng
        bookService.createBook(bookRequest);
        return "redirect:/books";
    }

    @GetMapping
    public String getBooks(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "12") int pageSize
    ) {
        Page<BookResponse> pageData = bookService.findAllBooks(page, pageSize);
        model.addAttribute("danhSachBook", pageData);
        model.addAttribute("currentPage", page);
        return "book/book-list";
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteByIdBook(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/update-form")
    public String forwardToUpdateForm(@PathVariable("id") Long id, Model model) {
        UpdateBookRequest updateBookRequest = bookService.findBookById(id);
        model.addAttribute("bookToiMuonCapNhat", updateBookRequest);
        return "book/update-book";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute("bookToiMuonCapNhat") @Valid UpdateBookRequest updateBookRequest,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/update-book";
        }
        bookService.updateBook(updateBookRequest);
        return "redirect:/books";
    }

    @GetMapping("/{id}/activations")
    public String changeBookActivation(@PathVariable("id") Long id) {
        bookService.changeBookActivation(id);
        return "redirect:/books";
    }
}
