package com.example.managelibraryrestful.repository;

import com.example.managelibraryrestful.entity.Borrow;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow , Long> {
}
