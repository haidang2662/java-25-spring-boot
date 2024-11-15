package com.example.basicauthenticationmanagelibrary.entity;

import com.example.basicauthenticationmanagelibrary.statics.BookStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String author;

    String publisher;

    Long price;

    @Enumerated(EnumType.STRING)
    BookStatus status;
}

