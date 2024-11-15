package vn.techmaster.danglh.recruitmentproject.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.CompanyRatingMode;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "company_ratings")
public class CompanyRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer rate;
    String comment;
    CompanyRatingMode ratingMode;
    Account account;

    LocalDate createdAt ;
    Integer createdBy ;
    LocalDate modifiedAt ;
    Integer modifiedBy ;
}
