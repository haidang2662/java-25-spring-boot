package com.example.managelibrarynothymeleaf.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchReaderDto {

    // dto - data transfer object

    Long id;

    String name;

    String phone;

    String address;

    LocalDate dob;

    String email;

    Long totalRecord;

}
