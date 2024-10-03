package com.example.homework01.model.request;

import com.example.homework01.constant.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {

    @NotBlank(message = "Email không được bỏ trống")
    @Email(message = "Email không đúng định dạng")
    @Size(max = 255, message = "Độ dài email nhỏ hơn 255 ký tự")
    private String email;

    @NotBlank(message = "Mật khẩu không được bỏ trống")
    @Size(min = 8, max = 16, message = "Độ dài mật khẩu phải từ 8 tới 16 ký tự")
    private String password;

    @NotBlank(message = "Tên không được bỏ trống")
    @Size(max = 255, message = "Độ dài của tên nhỏ hơn 255 ký tự")
    private String name;

    @NotNull(message = "Ngày sinh không được bỏ trống")
    @Past(message = "Ngày sinh phải là ngày quá khứ")
    private LocalDate dateOfBirth;

    @NotNull(message = "Giới tính không được bỏ trống")
    private Gender gender;


    private String hometown;

}
