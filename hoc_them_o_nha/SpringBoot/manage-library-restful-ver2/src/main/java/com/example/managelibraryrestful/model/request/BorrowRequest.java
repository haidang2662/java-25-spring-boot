package com.example.managelibraryrestful.model.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BorrowRequest {

    @NotNull(message = "Id sách không được bỏ trống")
    @Min(value = 1, message = "Id sách không âm")
    Long bookId;

    @NotNull(message = "Id bạn đọc không được bỏ trống")
    @Min(value = 1, message = "Id bạn đọc không âm")
    Long readerId;

    @NotNull(message = "Số lượng sách mượn không được bỏ trống")
    @Min(value = 1, message = "Số lượng sách mượn không âm")
    @Max(value = 5, message = "Số lượng sách mượn không quá 5")
    Long quantity;

    @NotNull(message = "Thời gian trả dự kiến không được bỏ trống ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Thời gian trả dự kiến phải là ngày tương lai")
    LocalDate expectedReturnDate;

}
