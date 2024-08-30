package com.example.buoi18.repository;

import com.example.buoi18.entity.Movie;
import com.example.buoi18.model.enums.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByName(String Name);

    List<Movie> findByNameContainingIgnoreCase(String name);

    Movie findByNameAndSlug(String name , String slug);

    List<Movie> findByRatingBetween(Double min , Double max);

    List<Movie> findByReleaseYearGreaterThanEqual(Integer year);

    List<Movie> findByStatusOrderByReleaseYearDesc(Boolean status);

    long countByType(MovieType type);

    boolean existsByRatingGreaterThan(Double rating);

    // Tim kiem cac bo phim theo loai va status sap xep theo thoi gian tao giam dan , rating tang dan va lay 5 ban ghi dau tien

    List<Movie> findTop5ByTypeAndStatusOrderByCreatedAtDesc();
}
