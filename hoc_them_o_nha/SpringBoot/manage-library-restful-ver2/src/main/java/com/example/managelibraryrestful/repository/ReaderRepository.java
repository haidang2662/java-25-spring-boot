package com.example.managelibraryrestful.repository;

import com.example.managelibraryrestful.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader , Long> {

}
