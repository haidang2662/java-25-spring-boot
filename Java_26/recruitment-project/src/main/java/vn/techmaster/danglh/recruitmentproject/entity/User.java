package vn.techmaster.danglh.recruitmentproject.entity;

import vn.techmaster.danglh.recruitmentproject.constant.Roles;
import vn.techmaster.danglh.recruitmentproject.constant.AccountStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    String username;

    String password;

//    boolean activated;

    @Enumerated(EnumType.STRING)
    AccountStatus status;

    @Enumerated(EnumType.STRING)
    Roles role;

    String avatar;

    LocalDateTime deletedDateTime;

    LocalDateTime activationMailSentAt;

    Integer activationMailSentCount;

    LocalDateTime forgotPasswordMailSentAt;

}
