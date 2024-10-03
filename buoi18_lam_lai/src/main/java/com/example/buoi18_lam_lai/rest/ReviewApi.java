package com.example.buoi18_lam_lai.rest;

import com.example.buoi18_lam_lai.entity.Review;
import com.example.buoi18_lam_lai.model.request.CreateReviewRequest;
import com.example.buoi18_lam_lai.model.request.UpdateReviewRequest;
import com.example.buoi18_lam_lai.model.response.ErrorResponse;
import com.example.buoi18_lam_lai.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor

// Chuyên trả về Json
// Request là 1 phần của controller

public class ReviewApi {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody CreateReviewRequest request) {
        try {
            Review review = reviewService.createReview(request);
            return ResponseEntity.ok(review);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    //api/reviews/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@RequestBody UpdateReviewRequest request , @PathVariable Integer id) {
        try {
            Review review = reviewService.updateReview(id , request);
            return ResponseEntity.ok(review);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
