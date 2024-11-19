package vn.techmaster.danglh.recruitmentproject.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {

    @NotBlank(message = "Email không được bỏ trống")
    @Size(max = 50 , message = "Email không được quá 50 ký tụ")
    String email;

    @NotBlank(message = "Password không được bỏ trống")
    @Size(max = 50 , message = "Password không được quá 50 ký tụ")
    String password;

    @NotBlank(message = "Tên không được bỏ trống")
    @Size(max = 50 , message = "Tên không được quá 50 ký tụ")
    String name;
}
