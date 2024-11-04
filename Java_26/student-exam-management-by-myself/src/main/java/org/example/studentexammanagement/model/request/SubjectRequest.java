package org.example.studentexammanagement.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectRequest {

    @NotBlank
    @Length(max = 255)
    String name;

    @NotNull
    @Min(value = 1)
    Integer credit;
}
