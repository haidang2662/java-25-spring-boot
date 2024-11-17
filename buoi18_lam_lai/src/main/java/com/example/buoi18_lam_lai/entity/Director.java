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
@Table(name = "directors")

public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true)
    String name;

    @Column(unique = true , nullable = false)
    String slug;

    String avatar;

    @Column(columnDefinition =  "Text")
    String bio;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}