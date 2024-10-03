package com.example.buoi18_lam_lai.service;

import com.example.buoi18_lam_lai.entity.Movie;
import com.example.buoi18_lam_lai.entity.Review;
import com.example.buoi18_lam_lai.entity.User;
import com.example.buoi18_lam_lai.model.request.CreateReviewRequest;
import com.example.buoi18_lam_lai.model.request.UpdateReviewRequest;
import com.example.buoi18_lam_lai.repository.MovieRepository;
import com.example.buoi18_lam_lai.repository.ReviewRepository;
import com.example.buoi18_lam_lai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;



    public Review createReview(CreateReviewRequest request) {
        // TODO : Fix user , sau này user sẽ là user đăng nhập
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id : " + userId));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id : " + request.getMovieId()));
                // TODO : bổ sung validate
        Review review = Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .movie(movie)
                .user(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return reviewRepository.save(review);
    }

    public Review updateReview(Integer id, UpdateReviewRequest request) {
        //Todo : fix user . Sau này user là user đăng nhập
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id : " + userId));
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id : " + id));
        // check user is owner of review
        if(!review.getUser().getId().equals(user.getId())){
            throw  new RuntimeException("You are not owner of this review");
        }

        // todo : bo sung validate rating , content
        review.setContent(request.getContent());
        review.setRating(request.getRating());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);

    }

    public void deleteReview(Integer id) {
        //Todo : fix user . Sau này user là user đăng nhập
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id : " + userId));
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id : " + id));
        // check user is owner of review
        if(!review.getUser().getId().equals(user.getId())){
            throw  new RuntimeException("You are not owner of this review");
        }
        reviewRepository.delete(review);
    }
}
