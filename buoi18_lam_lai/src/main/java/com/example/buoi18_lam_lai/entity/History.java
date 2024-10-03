package com.example.buoi18_lam_lai.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "histories")

public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Double duration;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "espisode_id")
    Episode episode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
