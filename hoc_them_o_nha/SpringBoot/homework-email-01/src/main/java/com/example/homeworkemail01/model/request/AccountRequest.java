package com.example.homeworkemail01.model.request;

import com.example.homeworkemail01.constant.AccountStatus;
import com.example.homeworkemail01.constant.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
//
//    @NotBlank
//    @Length(max = 255)
//    String name;

    @NotBlank
    String password;

    @NotBlank
    @Email
    @Length(max = 255)
    String email;

//    @NotBlank
//    String phone;
//
//    @NotBlank
//    @Length(max = 255)
//    String address;
//
//    @NotNull
//    @Past
//    LocalDate dob;
//
//    @NotNull
//    Gender gender;
//
//    @NotNull
//    AccountStatus status;
}
