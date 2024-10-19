package com.example.studentwebmanagement.repository;

import com.example.studentwebmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
