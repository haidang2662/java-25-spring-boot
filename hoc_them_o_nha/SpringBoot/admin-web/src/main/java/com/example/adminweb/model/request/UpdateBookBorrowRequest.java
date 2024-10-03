package com.example.adminweb.model.request;

import com.example.adminweb.entity.Book;
import com.example.adminweb.entity.User;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UpdateBookBorrowRequest {

    private Long id;

    @NotNull(message = "Người mượn không được bỏ trống")
    private User borrower;

    @NotNull(message = "Sách không được bỏ trống")
    private Book book;

    @NotNull(message = "Số lượng sách mượn không được bỏ trống")
    @Min(value = 1, message = "Số lượng sách mượn phải lớn hơn 0 và không quá 3")
    private Integer quantity;

    @NotNull(message = "Ngày trả dự kiến không được bỏ trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedReturnDate;

    @NotNull(message = "Ngày thuê không được bỏ trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

}
