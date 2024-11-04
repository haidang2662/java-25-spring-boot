package com.example.demo_send_email.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {

    @NotBlank
    @Email
    String email;

    String password;

    String fullName;


}
