package vn.techmaster.danglh.recruitmentproject.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.RegistrationType;

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

    @Size(max = 50 , message = "Tên trụ sở chính không được quá 50 ký tụ")
    String headQuarterAddress;

    @Min(value = 1 , message = "Số nhân viên không được âm")
    Integer employeeQuantity;

    @Size(max = 50 , message = "Tên website không được quá 50 ký tụ")
    String website;

    RegistrationType type;
}
