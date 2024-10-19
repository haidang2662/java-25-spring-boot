package com.example.adminweb.entity;

import com.example.adminweb.constant.TelephoneStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "telephone")
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate publishedAt;
    private BigDecimal price;
    private Double rating;
    private String publisher;
    private Integer totalTelephones;

    @Enumerated(EnumType.STRING)
    private TelephoneStatus status;

}
