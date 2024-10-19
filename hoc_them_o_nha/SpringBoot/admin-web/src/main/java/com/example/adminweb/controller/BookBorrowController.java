package com.example.adminweb.controller;

import com.example.adminweb.model.request.BookBorrowCreationRequest;
import com.example.adminweb.model.request.UpdateBookBorrowRequest;
import com.example.adminweb.model.response.BookBorrowResponse;
import com.example.adminweb.model.response.BookResponse;
import com.example.adminweb.model.response.UserResponse;
import com.example.adminweb.service.BookBorrowService;
import com.example.adminweb.service.BookService;
import com.example.adminweb.service.UserService;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/borrows")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookBorrowController {

    BookService bookService;

    UserService userService;

    BookBorrowService bookBorrowService;

    @GetMapping("/creation-form")
    public String showBookCreationForm(Model model) {
        model.addAttribute("bookBorrowRequest", new BookBorrowCreationRequest()); // Khởi tạo đối tượng borrow và đưa vào model

        List<BookResponse> activeBooks = bookService.findActiveBook();
        model.addAttribute("books", activeBooks);

        List<UserResponse> activeUsers = userService.findActiveUser();
        model.addAttribute("readers", activeUsers);

        return "borrow/borrow-creation";
    }

    @PostMapping
    public String bookBorrow(@ModelAttribute("bookBorrowRequest") @Valid BookBorrowCreationRequest bookBorrowRequest,
                             Errors errors, Model model) {
        // Nếu có lỗi, trả về trang đăng ký và hiển thị lỗi
        if (errors.hasErrors()) {
            model.addAttribute("bookBorrowRequest", bookBorrowRequest); // Đưa lại thông tin form vào model
            return "borrow/borrow-creation"; // Quay lại trang đăng ký
        }
        // Nếu không có lỗi, tiến hành đăng ký và chuyển hướng
        bookBorrowService.create(bookBorrowRequest);
        return "redirect:/borrows";
    }

    @GetMapping
    public String getBookBorrows(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "6") int pageSize
    ) {
        Page<BookBorrowResponse> bookBorrowResponses = bookBorrowService.findAllBookBorrows(page, pageSize);
        model.addAttribute("danhSachBookBorrow", bookBorrowResponses);
        model.addAttribute("currentPage", page);
        return "borrow/borrows";
    }

    @GetMapping("/{id}/delete")
    public String deleteBookBorrow(@PathVariable("id") Long id) {
        bookBorrowService.deleteByIdBookBorrow(id);
        return "redirect:/borrows";
    }

    @GetMapping("/{id}/update-form")
    public String forwardToUpdateForm(@PathVariable("id") Long id, Model model) {
        UpdateBookBorrowRequest updateBookBorrow = bookBorrowService.findBookBorrowById(id);
        model.addAttribute("bookBorrowToiMuonCapNhat", updateBookBorrow);

        List<BookResponse> activeBooks = bookService.findActiveBook();
        model.addAttribute("books", activeBooks);

        List<UserResponse> activeUsers = userService.findActiveUser();
        model.addAttribute("readers", activeUsers);

        return "borrow/update-borrow";
    }

    @PostMapping("/update")
    public String updateBookBorrow(@ModelAttribute("bookBorrowToiMuonCapNhat") @Valid UpdateBookBorrowRequest updateBookBorrowRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "borrow/update-borrow";
        }


//        // Tìm kiếm đối tượng User và Book dựa trên ID
//        User borrower = updateBookBorrowRequest.getBorrower(); // Giả sử bạn đã có phương thức tìm kiếm User
//        Book book = updateBookBorrowRequest.getBook();
//
//        // Kiểm tra nếu borrower là null, throw exception nếu cần
//        if (borrower == null) {
//            throw new ObjectNotFoundException("Không tìm thấy bạn đọc");
//        }
//
//        if (book == null) {
//            throw new ObjectNotFoundException("Không tìm thấy sách");
//        }
//
//

        bookBorrowService.updateBookBorrow(updateBookBorrowRequest);
        return "redirect:/borrows";
    }
}
