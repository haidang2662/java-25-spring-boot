package com.example.managelibrarynothymeleaf.repository;

import com.example.managelibrarynothymeleaf.entity.Reader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Page<Reader> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
