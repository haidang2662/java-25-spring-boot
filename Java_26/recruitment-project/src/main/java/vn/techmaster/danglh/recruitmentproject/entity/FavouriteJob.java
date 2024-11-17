package vn.techmaster.danglh.recruitmentproject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "favourite_jobs")
public class FavouriteJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @JoinColumn(name = "candidate_id")
    @ManyToOne(targetEntity = Candidate.class)
    Candidate candidate;

    @JoinColumn(name = "job_id")
    @ManyToOne(targetEntity = Job.class)
    Job job;

    LocalDate createdAt;
}
