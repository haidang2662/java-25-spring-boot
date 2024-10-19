package com.example.springrestful.model.response;

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

    String address;

    String email;

    LocalDate dob;

    Float gpa;

}
