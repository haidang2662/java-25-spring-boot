package com.example.buoi18_lam_lai.entity;

import com.example.buoi18_lam_lai.model.enums.UserRole;
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
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true)
    String name;

    @Column(unique = true)
    String email;

    String avatar;

    String password;

    @Enumerated(EnumType.STRING)
    UserRole role;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
