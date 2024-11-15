package vn.techmaster.danglh.recruitmentproject.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "candidate_cvs")
public class CandidateCvs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Candidate candidate;
    String cvUrl;
    Boolean main;
}
