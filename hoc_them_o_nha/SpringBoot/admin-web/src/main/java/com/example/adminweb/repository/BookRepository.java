package com.example.adminweb.repository;

import com.example.adminweb.constant.BookStatus;
import com.example.adminweb.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByStatus(BookStatus status);

}
