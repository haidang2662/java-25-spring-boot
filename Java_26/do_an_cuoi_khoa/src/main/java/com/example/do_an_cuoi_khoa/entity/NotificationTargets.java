package com.example.do_an_cuoi_khoa.entity;

import com.example.do_an_cuoi_khoa.constant.TargetType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "notification_targets")
public class NotificationTargets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Accounts account;
    Notifications notification;
    List<Integer> targetId;
    TargetType type;
    Boolean seen;

    LocalDate createdAt ;
    Integer createdBy ;
    LocalDate modifiedAt ;
    Integer modifiedBy ;

}
