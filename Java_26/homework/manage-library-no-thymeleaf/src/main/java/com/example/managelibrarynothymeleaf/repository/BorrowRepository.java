package com.example.managelibrarynothymeleaf.repository;

import com.example.managelibrarynothymeleaf.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow , Long> {
}
