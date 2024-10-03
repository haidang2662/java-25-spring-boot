package com.example.buoi18.service;

import com.example.buoi18.entity.Movie;
import com.example.buoi18.model.enums.MovieType;
import com.example.buoi18.repository.MovieRepository;
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

    public Page<Movie> getMoviesByType(MovieType movieType , boolean status , int page , int pageSize) {
        Pageable pageable = PageRequest.of(page , pageSize , Sort.by("createdAt").descending());
        return movieRepository.findByTypeAndStatus(movieType , status , pageable);
    }
}
