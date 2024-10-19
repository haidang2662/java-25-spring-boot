package com.example.managelibraryrestful.model.request;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReaderRequest {

    @NotBlank(message = "Tên bạn đọc không được trống")
    @Length(max = 200, message = "Tên bạn đọc không được vượt quá 200 ký tự")
    String name;

    @NotBlank(message = "SDT của bạn đọc không được trống")
    @Pattern(regexp = "0[0-9]{9}", message = "Số điện thoại không đúng định dạng")
    String phone;

    @NotBlank(message = "Địa chỉ của bạn đọc không được trống")
    @Length(max = 200, message = "Địa chỉ của bạn đọc không được vượt quá 200 ký tự")
    String address;

    @NotNull(message = "Ngày sinh không được bỏ trống")
    @Past(message = "Ngày sinh phải là ngày quá khứ")
    LocalDate dob;

    @NotBlank(message = "Email bạn đọc không được bỏ trống")
    @Email(message = "Email không đúng định dạng")
    String email;

}
