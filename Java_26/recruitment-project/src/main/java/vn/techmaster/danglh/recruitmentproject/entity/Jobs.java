package vn.techmaster.danglh.recruitmentproject.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "jobs")
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Companies company;
    String name ;
    String position ;
    Integer yearOfExperienceFrom ;
    Integer yearOfExperienceTo ;
    WorkingType workingType ;
    WorkingTimeType workingTimeType ;
    String workingAddress ;
    Literacy literacy;
    JobLevel level;
    Integer recruitingQuantity ;
    LocalDate expiredDate ;
    String skills ;
    String description ;
    String benefit ;
    String requirement ;
    Integer salaryFrom ;
    Integer salaryTo ;
    JobStatus status ;

    LocalDate createdAt ;
    Integer createdBy ;
    LocalDate modifiedAt ;
    Integer modifiedBy ;
}
