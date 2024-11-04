package com.example.manage_student_swagger.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FinalScoreRequest {

    @NotNull(message = "Id sinh viên không được bỏ trống")
    @Min(value = 1, message = "Id sinh viên không âm")
    Long studentId;

    @NotNull(message = "Id môn học không được bỏ trống")
    @Min(value = 1, message = "Id môn học không âm")
    Long subjectId;

    @NotNull(message = "Điểm môn học không được bỏ trống")
    @Min(value = 1, message = "Điểm môn học không âm")
    Float scoreSubject;

    @NotNull(message = "Ngày thi không được bỏ trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    LocalDate examDay;

}
