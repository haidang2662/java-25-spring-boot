package org.example.studentexammanagement.scheduler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.studentexammanagement.entity.ExamResult;
import org.example.studentexammanagement.entity.Student;
import org.example.studentexammanagement.exception.ObjectNotFoundException;
import org.example.studentexammanagement.repository.ExamResultRepository;
import org.example.studentexammanagement.repository.StudentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@Component
//@AllArgsConstructor
//public class StudentGpaCalculator {
//
//    ExamResultRepository examResultRepository;
//    StudentRepository studentRepository;
//
//    //    @Scheduled(cron = "0 0 0 * * *")
//    @Scheduled(fixedRate = 5000)
//    public void calculateAllStudentsGpa() {
//        List<Student> students = studentRepository.findAll();
//
//        for (Student student : students) {
//            try {
//                calculateStudentGpa(student.getId());
//            } catch (ObjectNotFoundException e) {
//                System.err.println("Lỗi khi tính GPA cho sinh viên ID: " + student.getId() + " - " + e.getMessage());
//            }
//        }
//    }
//
//    public void calculateStudentGpa(Long studentId) throws ObjectNotFoundException {
//        List<ExamResult> examResultList = examResultRepository.findByStudentId(studentId);
//        if(examResultList.isEmpty()){
//            throw new ObjectNotFoundException("Không tìm thấy điểm thi của sinh viên có id " + studentId);
//        }
//        float total_mark = 0;
//        for (ExamResult examResult : examResultList){
//            total_mark += examResult.getMark();
//        };
//        float gpa = total_mark/examResultList.size();
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
//        if(studentOptional.isEmpty()){
//            throw new ObjectNotFoundException("Không tìm thấy sinh viên có id " +studentId);
//        }
//        Student student = studentOptional.get();
//        student.setGpa(gpa);
//        studentRepository.save(student);
//    }
//
//}
