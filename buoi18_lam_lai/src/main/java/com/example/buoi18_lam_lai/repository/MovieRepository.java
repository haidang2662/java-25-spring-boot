package com.example.buoi18_lam_lai.repository;

import com.example.buoi18_lam_lai.entity.Blog;
import com.example.buoi18_lam_lai.entity.Movie;
import com.example.buoi18_lam_lai.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByName(String name);

    List<Movie> findByNameContainingIgnoreCase(String name);

    Movie findByNameAndSlug(String name, String slug);

    List<Movie> findByRatingBetween(Double min, Double max);

    List<Movie> findByReleaseYearGreaterThanEqual(Integer year);

    List<Movie> findByStatusOrderByReleaseYearDesc(Boolean status);

    Page<Movie> findByStatus(Boolean status, Pageable pageable);

    long countByType(MovieType type);

    boolean existsByRatingGreaterThan(Double rating);

    // Tim kiem cac bo phim theo loai va status sap xep theo thoi gian tao giam dan , rating tang dan va lay 5 ban ghi dau tien

    List<Movie> findTop5ByTypeAndStatusOrderByCreatedAtDesc(MovieType type, Boolean status);

    List<Movie> findByStatus(Boolean status, Sort sort);


    // Ung dung movie
    Page<Movie> findByTypeAndStatus(MovieType type, boolean status, Pageable pageable);

    Optional<Movie> findByIdAndSlugAndStatus(Integer id, String slug, boolean b);

    /***
     * Tất cả các hành động làm thay đổi dữ liệu (Thêm, sửa, xóa) có 2 cách
     * 1 - sử dụng hàm có sẵn
     * 2 - viết sql
     */
    @Modifying
    @Query("update Movie m set m.name = :name where m.id = :movieId")
    void capNhatTenPhim(String name, Integer movieId);

    @Modifying
    void deleteByNameContainingIgnoreCase(String name);

    @Modifying
    @Query("delete from Movie m where lower(m.name) like lower('%" + ":name" + "%')")
    void xoaPhimTheoTen(String name);

    Optional<Movie> findByIdAndSlugAndStatus(Integer id , String slug , Boolean status);

}
