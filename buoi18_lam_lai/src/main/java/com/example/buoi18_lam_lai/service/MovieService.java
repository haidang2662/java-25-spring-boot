package com.example.buoi18_lam_lai.service;

import com.example.buoi18_lam_lai.entity.Movie;
import com.example.buoi18_lam_lai.model.enums.MovieType;
import com.example.buoi18_lam_lai.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Page<Movie> getMoviesByType(MovieType type, boolean status, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending()); // 1h04'
        return movieRepository.findByTypeAndStatus(type, status, pageable);
    }

    public Movie getMovieDetails(Integer id, String slug) {
        return movieRepository.findByIdAndSlugAndStatus(id, slug, true)
                .orElse(null);
    }

    // update movie casch 1
    public Movie updateMovieName1(String name, Integer id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            movie.setName(name);
            movieRepository.save(movie);
        }
        return movie;
    }

    // update movie casch 2
    public void updateMovieName2(String name, Integer id) {
        movieRepository.capNhatTenPhim(name, id);
    }

    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    public void deleteMovie1(String name) {
        movieRepository.deleteByNameContainingIgnoreCase(name);
    }

    public void deleteMovie2(String name) {
        movieRepository.xoaPhimTheoTen(name);
    }

}
