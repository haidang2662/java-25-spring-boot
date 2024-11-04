package com.example.managelibraryrestful.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse {

    Long id;
    String name;
    Long price;
    String publisher;
    LocalDate publishedYear;
}
