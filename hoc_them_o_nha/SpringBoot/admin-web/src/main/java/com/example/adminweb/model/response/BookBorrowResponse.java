package com.example.adminweb.model.response;

import com.example.adminweb.entity.Book;
import com.example.adminweb.entity.User;
import jakarta.persistence.*;
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

    private User borrower; // người thuê

    private Book book; // sách được thuê

    private int quantity; // Số lượng sách mượn, không quá 3 quyển

    private LocalDate createdDate; // ngày thuê

    private LocalDate expectedReturnDate; // ngày dự kiến trả

}
