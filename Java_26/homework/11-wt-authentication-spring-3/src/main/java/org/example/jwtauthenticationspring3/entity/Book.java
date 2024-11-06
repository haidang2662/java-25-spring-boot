package org.example.jwtauthenticationspring3.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.jwtauthenticationspring3.statics.BookStatus;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
