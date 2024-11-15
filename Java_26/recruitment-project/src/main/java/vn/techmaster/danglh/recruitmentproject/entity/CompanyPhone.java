package vn.techmaster.danglh.recruitmentproject.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.PhoneType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "company_phone")
public class CompanyPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Companies company;
    String phone;
    PhoneType type;
    Boolean main;
}
