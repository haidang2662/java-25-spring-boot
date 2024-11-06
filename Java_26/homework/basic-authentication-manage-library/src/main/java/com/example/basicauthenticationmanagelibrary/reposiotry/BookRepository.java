package com.example.basicauthenticationmanagelibrary.reposiotry;

import com.example.basicauthenticationmanagelibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
