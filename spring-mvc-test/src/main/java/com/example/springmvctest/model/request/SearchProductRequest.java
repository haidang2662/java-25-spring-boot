package com.example.springmvctest.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchProductRequest {

    String name;
    String description;
    Integer minPrice;
    Integer maxPrice;

    int pageSize = 10;
    int pageNumber = 1;

}
