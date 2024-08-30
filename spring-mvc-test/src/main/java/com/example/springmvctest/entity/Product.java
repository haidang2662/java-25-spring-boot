package com.example.springmvctest.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    int id;
    String name;
    String description;
    String thumbnail;
    int price;
    double rating;
    int discount;

}
