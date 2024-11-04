package com.example.managelibraryrestful.repository;

import com.example.managelibraryrestful.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book , Long> {
}
