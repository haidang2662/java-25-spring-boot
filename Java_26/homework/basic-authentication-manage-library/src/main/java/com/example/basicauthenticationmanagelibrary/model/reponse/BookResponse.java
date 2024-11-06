package com.example.basicauthenticationmanagelibrary.model.reponse;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse {

    Long id;

    String name;

    String author;

    String publisher;

    Long price;
}
