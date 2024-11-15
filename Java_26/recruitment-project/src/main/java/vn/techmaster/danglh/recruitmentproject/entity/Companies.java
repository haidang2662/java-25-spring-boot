package vn.techmaster.danglh.recruitmentproject.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "companies")
public class Companies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    Accounts account;
    String alias; // Viết tắt
    LocalDate foundAt;
    String taxCode;
    String headQuarterAddress;
    Integer employeeQuantity;
    String website;
    String avatarUrl;
    String coverImageUrl;
    String description;
    Double rating;

    LocalDate createdAt ;
    Integer createdBy ;
    LocalDate modifiedAt ;
    Integer modifiedBy ;
}
