package com.example.do_an_cuoi_khoa.entity;


import com.example.do_an_cuoi_khoa.constant.ApplicationStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "applications")
public class Applications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Jobs job;
    CandidateCVS cvsId;
    Candidates candidate;
    String applicationDescription;
    ApplicationStatus status;
    String recruiterComment;
}
