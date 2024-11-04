package com.example.manage_student_swagger.service;

import com.example.manage_student_swagger.entity.FinalScore;
import com.example.manage_student_swagger.entity.Student;
import com.example.manage_student_swagger.exceptionHandling.ObjectNotFoundException;
import com.example.manage_student_swagger.model.request.SearchStudentRequest;
import com.example.manage_student_swagger.model.request.StudentRequest;
import com.example.manage_student_swagger.model.response.CommonSearchResponse;
import com.example.manage_student_swagger.model.response.StudentResponse;
import com.example.manage_student_swagger.repository.FinalScoreRepository;
import com.example.manage_student_swagger.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class StudentService {

    StudentRepository studentRepository;
    ObjectMapper objectMapper;
    FinalScoreRepository finalScoreRepository;

    public List<StudentResponse> getStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList
                .stream()
                .map(s -> objectMapper.convertValue(s, StudentResponse.class))
                .toList();
    }

    public StudentResponse createStudent(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setAddress(request.getAddress());
        student.setDob(request.getDob());
        student.setPhone(request.getPhone());
        student.setGpa(0f);
        studentRepository.save(student);
        return objectMapper.convertValue(student, StudentResponse.class);
    }


    public StudentResponse updateStudent(StudentRequest request, Long studentId) throws ObjectNotFoundException {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy sinh viên có id : " + studentId);
        }
        Student student = studentOptional.get();
        student.setName(request.getName());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setDob(request.getDob());
        student.setGpa(0f);
        studentRepository.save(student);
        return objectMapper.convertValue(student, StudentResponse.class);
    }

    ;

    public StudentResponse getStudentDetails(Long studentId) throws ObjectNotFoundException {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy sinh viên có id là " + studentId);
        }
        ;
        Student student = optionalStudent.get();
        return objectMapper.convertValue(student, StudentResponse.class);
    }

    // Lay diem thi cao nhat cua tung mon roi tinh gpa
    public Float updateStudentScoreSubject(Long studentId) throws ObjectNotFoundException {

        List<FinalScore> finalScores = finalScoreRepository.findByStudentId(studentId);
        if (finalScores.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy điểm thi của sinh viên có id " + studentId);
        }

        // Lay ra tem cac mon hoc tu List ma khong bi trung
        Set<Long> subjectIds = new HashSet<>();
        for (int i = 0; i < finalScores.size(); i++) {
            subjectIds.add(finalScores.get(i).getSubject().getId());
        }

        List<FinalScore> finalScoreResult = new ArrayList<>();

        subjectIds.forEach(subjectId -> {
            // Đoạn code này lọc ra danh sách các kết quả thi theo từng môn học
            List<FinalScore> tempFinalScores = finalScores
                    .stream()
                    .filter(f -> Objects.equals(f.getSubject().getId(), subjectId))
                    .toList();

            //Lay ra diem thi cao nhat
            FinalScore max = tempFinalScores.get(0);
            if (tempFinalScores.size() == 1) {
                finalScoreResult.add(max);
                return;
            }

            for (int i = 0; i < tempFinalScores.size(); i++) {
                if (tempFinalScores.get(i).getScoreSubject() > max.getScoreSubject()) {
                    max = tempFinalScores.get(i);
                }
            }
            finalScoreResult.add(max);
        });
        float tuSo = 0f;
        int mauSo = 0;
        for (int i = 0; i < finalScoreResult.size(); i++) {
            FinalScore result = finalScoreResult.get(i);
            tuSo += result.getScoreSubject() * result.getSubject().getTotalCredits();
            mauSo += result.getSubject().getTotalCredits();
        }
        if (mauSo == 0) {
            return 0f;
        }
        return tuSo / mauSo;
    }

    public CommonSearchResponse<StudentResponse> searchStudent(SearchStudentRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize());
        Page<Student> studentPage = studentRepository.findByNameContainingIgnoreCase(request.getName(), pageable);
        List<StudentResponse> studentResponses = studentPage
                .map(s -> objectMapper.convertValue(s, StudentResponse.class))
                .toList();
        return CommonSearchResponse.<StudentResponse>builder()
                .totalRecord(studentPage.getTotalElements())
                .totalPage(studentPage.getTotalPages())
                .data(studentResponses)
                .paging(new CommonSearchResponse.CommonSearchPageInfo(request.getPageSize(), request.getPageIndex()))
                .build();
    }
}
