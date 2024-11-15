package com.example.do_an_cuoi_khoa.entity;

import com.example.do_an_cuoi_khoa.constant.AccountStatus;
import com.example.do_an_cuoi_khoa.constant.Roles;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String email ;
    String password ;
    Roles role;
    AccountStatus status;
    LocalDate activationMailSentAt ;
    Integer activationMailSentCount ;
    LocalDate forgotPasswordMailSentAt ;
    Companies company;
    Candidates candidate;
}
