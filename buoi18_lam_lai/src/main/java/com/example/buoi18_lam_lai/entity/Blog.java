package com.example.buoi18_lam_lai.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;

    String slug;

    @Column(columnDefinition = "Text")
    String description;

    @Column(columnDefinition = "MEDIUMText")
    String content;

    String thumbnail;

    Boolean status;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime publishedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
