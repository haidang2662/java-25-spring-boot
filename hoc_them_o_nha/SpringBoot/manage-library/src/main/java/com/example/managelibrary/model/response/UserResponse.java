package com.example.managelibrary.model.response;

import com.example.managelibrary.constant.Gender;
import com.example.managelibrary.constant.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String email;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private String hometown;
    private UserStatus status;

}
