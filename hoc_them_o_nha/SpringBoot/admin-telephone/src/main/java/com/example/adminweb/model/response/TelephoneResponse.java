package com.example.adminweb.model.response;

import com.example.adminweb.constant.TelephoneStatus;
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
public class TelephoneResponse {

    private Long id;
    private String name;
    private LocalDate publishedAt;
    private BigDecimal price;
    private Double rating;
    private String publisher;
    private Integer totalTelephones;
    private TelephoneStatus status;

}
