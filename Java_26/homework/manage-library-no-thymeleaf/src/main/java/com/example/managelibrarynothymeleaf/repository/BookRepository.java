package com.example.managelibrarynothymeleaf.repository;

import com.example.managelibrarynothymeleaf.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
