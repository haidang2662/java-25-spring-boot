package com.example.adminweb.model.response;

import com.example.adminweb.constant.Gender;
import com.example.adminweb.constant.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String email;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String hometown;
    private UserStatus status;

}