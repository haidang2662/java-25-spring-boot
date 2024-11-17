package org.example.studentexammanagement.repository;

import org.example.studentexammanagement.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContainingIgnoreCase(String name);
    Page<Student> findByNameContainingIgnoreCase(String name , Pageable pageable);

}