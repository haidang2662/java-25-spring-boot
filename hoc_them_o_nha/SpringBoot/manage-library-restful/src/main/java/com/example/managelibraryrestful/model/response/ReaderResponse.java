package com.example.managelibraryrestful.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReaderResponse {

    Long id;

    String name;

    String phone;

    String address;

    LocalDate dob;

    String email;
}
