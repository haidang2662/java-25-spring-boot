package vn.techmaster.danglh.recruitmentproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import vn.techmaster.danglh.recruitmentproject.constant.*;

import java.time.LocalDate;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "jobs")
public class Job extends BaseEntity{

    @JoinColumn(name = "company_id")
    @ManyToOne(targetEntity = Company.class)
    Company company;

    String name ;
    String position ;
    Integer yearOfExperienceFrom ;
    Integer yearOfExperienceTo ;

    @Enumerated(EnumType.STRING)
    WorkingType workingType ;

    @Enumerated(EnumType.STRING)
    WorkingTimeType workingTimeType ;

    String workingAddress ;

    @Enumerated(EnumType.STRING)
    Literacy literacy;

    @Enumerated(EnumType.STRING)
    JobLevel level;

    Integer recruitingQuantity ;
    LocalDate expiredDate ;
    String skills ;
    String description ;
    String benefit ;
    String requirement ;
    Integer salaryFrom ;
    Integer salaryTo ;

    @Enumerated(EnumType.STRING)
    JobStatus status ;


}
