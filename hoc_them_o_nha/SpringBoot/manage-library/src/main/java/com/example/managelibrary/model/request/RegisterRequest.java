package com.example.managelibrary.model.request;

import com.example.managelibrary.constant.Gender;
import com.example.managelibrary.constant.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {

    @NotBlank(message = "email không được bỏ trống")
    @Email(message = "Phải đúng định dạng email")
    private String email;

    @NotBlank(message = "password không được bỏ trống")
    private String password;

    @NotBlank(message = "name không được bỏ trống")
    private String name;

    @NotNull(message = "Ngày sinh không được bỏ trống")
    @Past(message = "Ngày sinh phải là quá khứ")
    private LocalDate dob;

    @NotNull(message = "Giới tính không được bỏ trống")
    private Gender gender;

    private String hometown;

    @NotNull(message = "Trạng tháu không được bỏ trống")
    private UserStatus status;

}
