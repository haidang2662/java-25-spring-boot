package com.example.adminweb.repository;

import com.example.adminweb.entity.BookBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowRepository extends JpaRepository<BookBorrow , Long> {

}
