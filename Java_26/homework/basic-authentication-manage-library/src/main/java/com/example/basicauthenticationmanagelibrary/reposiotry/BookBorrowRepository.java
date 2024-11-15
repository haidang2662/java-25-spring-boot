package com.example.basicauthenticationmanagelibrary.reposiotry;

import com.example.basicauthenticationmanagelibrary.entity.BookBorrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBorrowRepository extends JpaRepository<BookBorrow , Long> {
}
