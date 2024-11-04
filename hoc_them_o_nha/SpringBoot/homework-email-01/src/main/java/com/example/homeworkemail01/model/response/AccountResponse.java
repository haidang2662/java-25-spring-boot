package com.example.homeworkemail01.model.response;

import com.example.homeworkemail01.constant.AccountStatus;
import com.example.homeworkemail01.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    Long id;
    String name;
    String email;
    String phone;
    String address;
    LocalDate dob;
    Gender gender;
    AccountStatus status;
}
