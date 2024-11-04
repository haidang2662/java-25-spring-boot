package com.example.managelibraryrestful.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest {

    @NotBlank(message = "Tên đầu sách không được bỏ trống")
    @Length(max = 200, message = "Tên đầu sách không được vượt quá 200 ký tự")
    String name;

    @NotNull(message = "Giá của đầu sách không được bỏ trống")
    @Min(value = 1, message = "Giá của đầu sách không âm")
    Long price;

    @NotBlank(message = "Tên nhà sản xuất không được bỏ trống")
    @Length(max = 200, message = "Tên nhà sản xuất không được vượt quá 200 ký tự")
    String publisher;

    @NotNull(message = "Năm xuất bản không được bỏ trống")
    @Past(message = "Năm xuất bản phải là quá khứ")
    LocalDate publishedYear;
}
