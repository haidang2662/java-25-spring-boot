package com.example.adminweb.entity;

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
@Entity
@Table(name = "bookBorrow")
public class BookBorrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Mỗi người thuê có thể thuê nhiều lần, nhưng một lần thuê chỉ thuộc về một người
    @JoinColumn(name = "borrower_id", referencedColumnName = "id")
    private User borrower; // người thuê

    @ManyToOne // Mỗi cuốn sách có thể được thuê nhiều lần, nhưng một lần thuê chỉ thuộc về một cuốn sách
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book; // sách được thuê

    @Column(nullable = false)
    private int quantity; // Số lượng sách mượn, không quá 3 quyển

    private LocalDate createdDate; // ngày thuê

    private LocalDate expectedReturnDate; // ngày dự kiến trả

}
