package vn.techmaster.danglh.recruitmentproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.danglh.recruitmentproject.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
