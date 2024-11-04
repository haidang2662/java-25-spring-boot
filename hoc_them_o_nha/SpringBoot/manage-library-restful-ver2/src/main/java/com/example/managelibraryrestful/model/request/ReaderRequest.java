package com.example.managelibraryrestful.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "readerRequest", description = "Thông tin bạn đọc cần tạo mới")
public class ReaderRequest {

    @Schema(name = "name", description = "Tên bạn đọc")
    @NotBlank(message = "Tên bạn đọc không được trống")
    @Length(max = 200, message = "Tên bạn đọc không được vượt quá 200 ký tự")
    String name;

    @Schema(name = "phone", description = "SDT của bạn đọc")
    @NotBlank(message = "SDT của bạn đọc không được trống")
    @Pattern(regexp = "0[0-9]{9}", message = "Số điện thoại không đúng định dạng")
    String phone;

    @Schema(name = "address", description = "Địa chỉ của bạn đọc")
    @NotBlank(message = "Địa chỉ của bạn đọc không được trống")
    @Length(max = 200, message = "Địa chỉ của bạn đọc không được vượt quá 200 ký tự")
    String address;

    @Schema(name = "dob", description = "Ngày sinh của bạn đọc (không dưới 18 tuổi)")
    @NotNull(message = "Ngày sinh không được bỏ trống")
    @Past(message = "Ngày sinh phải là ngày quá khứ")
    LocalDate dob;

    @Schema(name = "email", description = "Email của bạn đọc")
    @NotBlank(message = "Email bạn đọc không được bỏ trống")
    @Email(message = "Email không đúng định dạng")
    String email;

}
