package vn.techmaster.danglh.recruitmentproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.danglh.recruitmentproject.entity.CandidateCv;

@Repository
public interface CandidateCvRepository extends JpaRepository<CandidateCv , Long> {
}
