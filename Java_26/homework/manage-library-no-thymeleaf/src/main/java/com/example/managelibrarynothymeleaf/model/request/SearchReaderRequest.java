package com.example.managelibrarynothymeleaf.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchReaderRequest extends CommonSearchRequest{

    String name;
    String email;
    String phone;
    String address;
}
