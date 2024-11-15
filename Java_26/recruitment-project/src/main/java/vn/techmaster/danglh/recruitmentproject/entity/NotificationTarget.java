package vn.techmaster.danglh.recruitmentproject.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.TargetType;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "notification_targets")
public class NotificationTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Account account;
    Notification notification;
    Integer targetId;
    TargetType type;
    Boolean seen;

    LocalDate createdAt ;
    Integer createdBy ;
    LocalDate modifiedAt ;
    Integer modifiedBy ;

}
