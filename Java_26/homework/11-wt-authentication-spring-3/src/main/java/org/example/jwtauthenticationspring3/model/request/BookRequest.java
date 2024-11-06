package org.example.jwtauthenticationspring3.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BookRequest {

    @NotBlank
    @Length(max = 255)
    String name;

    @NotBlank
    @Length(max = 255)
    String author;

    @NotBlank
    @Length(max = 255)
    String publisher;

    @NotNull
    @Min(value = 1)
    Long price;
}
