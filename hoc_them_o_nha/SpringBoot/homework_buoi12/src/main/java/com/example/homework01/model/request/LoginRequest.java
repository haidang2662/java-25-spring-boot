package com.example.homework01.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Email không được bỏ trống")
    @Email(message = "Email không đúng định dạng")
    @Size(max = 255, message = "Độ dài email nhỏ hơn 255 ký tự")
    private String email;

    @NotBlank(message = "Mật khẩu không được bỏ trống")
    @Size(min = 8, max = 16, message = "Độ dài mật khẩu phải từ 8 tới 16 ký tự")
    private String password;

}
