package com.example.do_an_cuoi_khoa.entity;

import com.example.do_an_cuoi_khoa.constant.InterviewStatus;
import com.example.do_an_cuoi_khoa.constant.InterviewType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "interviews")
public class Interviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Applications application;
    LocalDateTime invitationEmailSentAt ;
    LocalDateTime interview_at ;
    String interviewAddress;
    InterviewType interviewType ;
    Integer interviewStep ;
    InterviewStatus status;
    String note ;

    LocalDate createdAt ;
    Integer createdBy ;
    LocalDate modifiedAt ;
    Integer modifiedBy ;
}
