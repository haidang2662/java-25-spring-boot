package com.example.buoi18_lam_lai.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateReviewRequest {

    String content;
    Integer rating;
    Integer movieId;

}
