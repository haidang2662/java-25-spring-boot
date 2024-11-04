package com.example.manage_student_swagger.model.request;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequest {

    @NotBlank(message = "Tên sinh viên không được bỏ trống")
    @Length(max = 200, message = "Tên sinh viên không được vượt quá 200 ký tự")
    String name;

    @NotBlank(message = "SDT của sinh viên không được trống")
//    @Pattern(regexp = "0[0-9]{9}", message = "Số điện thoại không đúng định dạng")
    String phone;

    @NotNull(message = "Ngày sinh không được bỏ trống")
    @Past(message = "Ngày sinh phải là ngày quá khứ")
    LocalDate dob;

    @NotNull(message = "GPA không được bỏ trống")
    @Min(value = 0 , message = "Điểm GPA không được âm")
    Float gpa;

    @NotBlank(message = "Địa chỉ của bạn đọc không được trống")
    @Length(max = 200, message = "Địa chỉ của bạn đọc không được vượt quá 200 ký tự")
    String address;

}
