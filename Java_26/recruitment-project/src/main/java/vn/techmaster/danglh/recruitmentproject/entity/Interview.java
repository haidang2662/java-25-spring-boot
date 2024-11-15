package vn.techmaster.danglh.recruitmentproject.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.InterviewStatus;
import vn.techmaster.danglh.recruitmentproject.constant.InterviewType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "interviews")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Application application;
    LocalDateTime invitationEmailSentAt ;
    LocalDateTime interview_at ;
    String interviewAddress;
    InterviewType interviewType ;
    Integer interviewStep ;
    InterviewStatus status;
    String note ;

    LocalDate createdAt ;
    Integer createdBy ;
    LocalDate modifiedAt ;
    Integer modifiedBy ;
}
