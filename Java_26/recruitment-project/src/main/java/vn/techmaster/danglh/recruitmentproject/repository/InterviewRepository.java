package vn.techmaster.danglh.recruitmentproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.danglh.recruitmentproject.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
}
