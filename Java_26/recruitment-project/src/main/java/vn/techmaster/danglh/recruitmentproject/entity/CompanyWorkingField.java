package vn.techmaster.danglh.recruitmentproject.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "company_working_fields")
public class CompanyWorkingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Account account;
    String name;
}
