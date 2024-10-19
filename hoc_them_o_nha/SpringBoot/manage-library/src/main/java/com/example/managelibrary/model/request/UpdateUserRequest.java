package com.example.managelibrary.model.request;

import com.example.managelibrary.constant.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UpdateUserRequest {
    private long id;

    @NotBlank(message = "Email không được bỏ trống")
    @Email(message = "Email không đúng định dạng")
    @Size(max = 255, message = "Độ dài email nhỏ hơn 255 ký tự")
    private String email;

    @NotBlank(message = "Tên không được bỏ trống")
    @Size(max = 255, message = "Độ dài của tên nhỏ hơn 255 ký tự")
    private String name;

    @NotNull(message = "Ngày sinh không được bỏ trống")
    @Past(message = "Ngày sinh phải là ngày quá khứ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotNull(message = "Giới tính không được bỏ trống")
    private Gender gender;


    private String hometown;
}
