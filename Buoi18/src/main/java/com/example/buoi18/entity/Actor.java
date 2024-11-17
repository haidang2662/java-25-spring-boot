package com.example.buoi18.entity;

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
@Table(name = "actors")

public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true)
    String name;

    @Column(unique = true , nullable = false)
    String slug;

    String avartar;

    @Column(columnDefinition =  "Text")
    String bio;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}