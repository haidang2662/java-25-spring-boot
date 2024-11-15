package vn.techmaster.danglh.recruitmentproject.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.NotificationStatus;
import vn.techmaster.danglh.recruitmentproject.constant.NotificationType;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Account account;
    String title ;
    String content ;
    NotificationStatus status ;
    NotificationType type ;
    LocalDate startAt ;
    LocalDate finishAt ;

    LocalDate createdAt ;
    Integer createdBy ;
    LocalDate modifiedAt ;
    Integer modifiedBy ;
}
