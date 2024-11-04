package com.example.managelibraryrestful.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "borrows")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    Book book;

    @ManyToOne
    @JoinColumn(name = "reader_id", referencedColumnName = "id")
    Reader reader;

    Long quantity;

    LocalDate borrowDate;

    LocalDate expectedReturnDate;

}
