package com.example.adminweb.model.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UpdateTelephoneBuyRequest {

    private Long id;

    @NotNull(message = "ID của người mua không được bỏ trống")
    @Min(value = 1, message = "ID của người mua phải là số dương")
    private Long buyerId;

    @NotNull(message = "ID của điện thoại không được bỏ trống")
    @Min(value = 1, message = "ID của điện thoại phải là số dương")
    private Long telephoneId;

    @NotNull(message = "Số lượng sách mượn không được bỏ trống")
    @Min(value = 1, message = "Số lượng sách mượn phải lớn hơn 0 và không quá 3")
    private Integer quantity;

    private LocalDate createdDate;


}
