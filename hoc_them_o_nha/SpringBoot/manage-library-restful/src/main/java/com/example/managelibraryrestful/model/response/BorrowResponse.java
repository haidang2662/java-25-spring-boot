package com.example.managelibraryrestful.model.response;

import com.example.managelibraryrestful.entity.Book;
import com.example.managelibraryrestful.entity.Reader;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BorrowResponse {

    Long id;

    Book book;

    Reader reader;

    Long quantity;

    LocalDate borrowDate;

    LocalDate expectedReturnDate;

}
