package com.example.manage_student_swagger.model.response;

import com.example.manage_student_swagger.constant.AcademicAbility;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponse {

    Long id;

    String name;

    String phone;

    LocalDate dob;

    Float gpa;

    String address;


}
