package com.example.managelibraryrestful.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reader")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String phone;

    String address;

    LocalDate dob;

    String email;

}
