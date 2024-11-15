package com.example.do_an_cuoi_khoa.entity;

import com.example.do_an_cuoi_khoa.constant.Gender;
import com.example.do_an_cuoi_khoa.constant.Literacy;
import com.example.do_an_cuoi_khoa.constant.WorkingTimeType;
import com.example.do_an_cuoi_khoa.constant.WorkingType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "candidates")
public class Candidates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Accounts account;
    String name;
    LocalDate dob;
    Gender gender;
    String phone;
    String address;
    String avatar;
    String skills;
    Double yearOfExperience;
    Literacy literacy;
    String graduatedAt;
    Integer expectedSalaryFrom ;
    Integer expectedSalaryTo ;
    WorkingTimeType expectedWorkingTimeType;
    WorkingType expectedWorkingType ;

    LocalDate createdAt ;
    Integer createdBy ;
    LocalDate modifiedAt ;
    Integer modifiedBy ;
}
