package com.example.do_an_cuoi_khoa.entity;

import com.example.do_an_cuoi_khoa.constant.NotificationStatus;
import com.example.do_an_cuoi_khoa.constant.NotificationType;
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
@Table(name = "notifications")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Accounts account;
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
