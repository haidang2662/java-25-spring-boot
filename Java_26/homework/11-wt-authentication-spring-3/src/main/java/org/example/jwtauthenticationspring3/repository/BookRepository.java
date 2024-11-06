package org.example.jwtauthenticationspring3.repository;

import org.example.jwtauthenticationspring3.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
