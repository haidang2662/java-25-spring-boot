package com.example.basicauthenticationmanagelibrary.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "borrows")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne // Mỗi người thuê có thể thuê nhiều lần, nhưng một lần thuê chỉ thuộc về một người
    @JoinColumn(name = "borrower_id", referencedColumnName = "id")
    private User borrower;

    @ManyToOne // Mỗi cuốn sách có thể được thuê nhiều lần, nhưng một lần thuê chỉ thuộc về một cuốn sách
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    private int quantity;

    private LocalDate createdDate;

    private LocalDate expectedReturnDate;
}
