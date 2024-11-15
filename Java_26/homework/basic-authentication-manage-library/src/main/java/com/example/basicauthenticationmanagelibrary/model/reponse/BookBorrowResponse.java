package com.example.basicauthenticationmanagelibrary.model.reponse;

import com.example.basicauthenticationmanagelibrary.entity.Book;
import com.example.basicauthenticationmanagelibrary.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookBorrowResponse {


    private int id;

    private User borrower;

    private Book book;

    private int quantity;

    private LocalDate createdDate;

    private LocalDate expectedReturnDate;

}
