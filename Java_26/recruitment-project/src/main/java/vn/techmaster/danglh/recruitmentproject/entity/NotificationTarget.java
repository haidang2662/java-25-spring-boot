package vn.techmaster.danglh.recruitmentproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import vn.techmaster.danglh.recruitmentproject.constant.TargetType;

import java.time.LocalDate;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "notification_targets")
public class NotificationTarget extends BaseEntity{


    @JoinColumn(name = "account_id")
    @ManyToOne(targetEntity = Account.class)
    Account account;

    @JoinColumn(name = "notification_id")
    @ManyToOne(targetEntity = Notification.class)
    Notification notification;

    Integer targetId;

    @Enumerated(EnumType.STRING)
    TargetType type;

    @Column(columnDefinition = "boolean default false")
    boolean seen;

}
