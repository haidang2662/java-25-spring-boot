package vn.techmaster.danglh.recruitmentproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import vn.techmaster.danglh.recruitmentproject.constant.AccountStatus;
import vn.techmaster.danglh.recruitmentproject.constant.Role;

import java.time.LocalDateTime;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends BaseEntity {

    String email;

    String password;

    @Enumerated(EnumType.STRING)
    AccountStatus status;

    @Enumerated(EnumType.STRING)
    Role role;

    String avatar;

    @JoinColumn(name = "company_id")
    @OneToOne(targetEntity = Company.class)
    Company company;

    @JoinColumn(name = "candidate_id")
    @OneToOne(targetEntity = Candidate.class)
    Candidate candidate;

    LocalDateTime deletedDateTime;

    LocalDateTime activationMailSentAt;

    Integer activationMailSentCount;

    LocalDateTime forgotPasswordMailSentAt;

}
