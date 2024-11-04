package com.example.manage_student_swagger.repository;

import com.example.manage_student_swagger.entity.FinalScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalScoreRepository extends JpaRepository<FinalScore , Long> {
    List<FinalScore> findByStudentId(Long studentId);

    List<FinalScore> findByStudentIdAndSubjectId(Long studentId, Long subjectId);
}
