package vn.techmaster.danglh.recruitmentproject.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotBlank(message = "")
    @Size(max = 50, message = "")
    @Email(message = "")
    String email;

    @NotBlank(message = "")
    @Size(max = 50, message = "")
    String password;

}
