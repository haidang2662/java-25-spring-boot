package com.example.buoi18_lam_lai.repository;

import com.example.buoi18_lam_lai.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Page<Blog> findByStatus(Boolean status , Pageable pageable);

    Optional<Blog> findByIdAndSlugAndStatus(Integer id , String slug , Boolean status);

}
