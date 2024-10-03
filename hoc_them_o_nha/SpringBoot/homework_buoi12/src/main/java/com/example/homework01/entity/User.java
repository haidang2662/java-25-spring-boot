package com.example.homework01.entity;

import com.example.homework01.constant.Gender;
import com.example.homework01.constant.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String hometown;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

}