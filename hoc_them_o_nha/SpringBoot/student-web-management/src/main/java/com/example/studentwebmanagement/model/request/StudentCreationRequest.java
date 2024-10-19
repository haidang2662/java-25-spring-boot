package com.example.studentwebmanagement.model.request;

import com.example.studentwebmanagement.constant.Gender;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCreationRequest {

    String name;

    String address;

    Gender gender;

    @NotNull(message = ".....")
    LocalDate dob;

    @Min(value = 0, message = "Điểm trung bình không được âm")
    @Max(value = 10, message = "Điểm trung bình không được vượt quá 10")
    float gpa;

}
