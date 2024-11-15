package com.example.basicauthenticationmanagelibrary.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookBorrowRequest {
    @NotNull(message = "ID của người mượn không được bỏ trống")
    @Min(value = 1, message = "ID của người mượn phải là số dương")
    private Long readerId;

    @NotNull(message = "ID của sách không được bỏ trống")
    @Min(value = 1, message = "ID của sách phải là số dương")
    private Long bookId;

    @NotNull(message = "Số lượng sách mượn không được bỏ trống")
    @Min(value = 1, message = "Số lượng sách mượn phải lớn hơn 0")
    private Integer quantity;

    @NotNull(message = "Ngày trả dự kiến không được bỏ trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedReturnDate;
}
