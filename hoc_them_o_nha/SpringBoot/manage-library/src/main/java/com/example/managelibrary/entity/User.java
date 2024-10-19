package com.example.managelibrary.entity;

import com.example.managelibrary.constant.Gender;
import com.example.managelibrary.constant.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String hometown;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

}
