package com.example.manage_student_swagger.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectRequest {

    @NotBlank(message = "Tên môn học không được bỏ trống")
    @Length(max = 200, message = "Tên môn học không được vượt quá 200 ký tự")
    String name;

    @NotNull(message = "Tổng số tín chỉ không được bỏ trống")
    @Min(value = 1 , message = "Tổng số tín chỉ không được âm")
    Long totalCredits;

}
