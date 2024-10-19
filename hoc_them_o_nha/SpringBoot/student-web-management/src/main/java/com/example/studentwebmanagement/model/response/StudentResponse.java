package com.example.studentwebmanagement.model.response;

import com.example.studentwebmanagement.constant.Gender;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponse {

    Long id;

    String name;

    String address;

    Gender gender;

    LocalDate dob;

    float gpa;

}
