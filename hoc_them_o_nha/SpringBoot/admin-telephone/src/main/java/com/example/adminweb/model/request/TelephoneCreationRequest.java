package com.example.adminweb.model.request;

import com.example.adminweb.constant.TelephoneStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TelephoneCreationRequest {

    @NotBlank(message = "Tên không được bỏ trống")
    @Size(max = 255, message = "Độ dài của tên nhỏ hơn 255 ký tự")
    private String name;

    @NotNull(message = "Ngày sản xuất không được bỏ trống")
    @Past(message = "Ngày sản xuất phải là ngày quá khứ")
    private LocalDate publishedAt;

    @NotNull(message = "Giá quyển sách không được bỏ trống")
    @Digits(integer=10, fraction=2, message="Giá sách phải là số và có tối đa 2 chữ số thập phân")
    private BigDecimal price;

    @NotNull(message = "Đánh giá sách không được bỏ trống")
    @DecimalMin(value = "1.0", message = "Đánh giá phải từ 1.0 trở lên")
    @DecimalMax(value = "5.0", message = "Đánh giá phải từ 5.0 trở xuống")
    private Double rating;


    @NotBlank(message = "Tên NXB không được bỏ trống")
    @Size(max = 255, message = "Độ dài của NXB nhỏ hơn 255 ký tự")
    private String publisher;

    @NotNull(message = "Tổng số điện thoại không được bỏ trống")
    @Digits(integer=10, fraction=0, message="Tổng số điện thoại phải là số và 0 chữ số thập phân")
    private Integer totalTelephones;

    @NotNull(message = "Trạng thái không được bỏ trống")
    private TelephoneStatus status;
}
