package org.example.jwtauthenticationspring3.model.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    @NotBlank
    @Size(max = 100)
    String username;

    @NotBlank
    String password;

    @NotBlank
    @Length(max = 255)
    String email;

    @NotBlank
    @Length(max = 255)
    String phone;

    @NotBlank
    @Length(max = 255)
    String address;
}
