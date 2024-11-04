package org.example.studentexammanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.example.studentexammanagement.entity.ExamResult;
import org.example.studentexammanagement.entity.Student;
import org.example.studentexammanagement.exception.ObjectNotFoundException;
import org.example.studentexammanagement.model.request.SearchStudentRequest;
import org.example.studentexammanagement.model.request.StudentRequest;
import org.example.studentexammanagement.model.response.CommonSearchStudentResponse;
import org.example.studentexammanagement.model.response.StudentResponse;
import org.example.studentexammanagement.repository.ExamResultRepository;
import org.example.studentexammanagement.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class StudentService {

    ObjectMapper objectMapper;

    StudentRepository studentRepository;

    ExamResultRepository examResultRepository;

    public CommonSearchStudentResponse<StudentResponse> searchStudent(SearchStudentRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex() , request.getPageSize());
        Page<Student> studentPage = studentRepository.findByNameContainingIgnoreCase(request.getName(), pageable);

        int totalPages = studentPage.getTotalPages();
        List<StudentResponse> studentResponses = studentPage
                .map(s -> objectMapper.convertValue(s , StudentResponse.class))
                .toList();
        return CommonSearchStudentResponse.<StudentResponse>builder()
                .totalRecord(studentPage.getTotalElements())
                .totalPage(totalPages)
                .data(studentResponses)
                .paging(new CommonSearchStudentResponse.CommonSearchPageInfo(request.getPageSize(), request.getPageIndex()))
                .build();
    }

    public StudentResponse getStudentById(Long id) throws ObjectNotFoundException {
        return studentRepository.findById(id)
                .map(s -> objectMapper.convertValue(s, StudentResponse.class))
                .orElseThrow(() -> new ObjectNotFoundException("Student not found"));
    }

    public StudentResponse createStudent(StudentRequest request) {
        Student student = objectMapper.convertValue(request, Student.class);
        student.setGpa(0f);
        student = studentRepository.save(student);
        return objectMapper.convertValue(student, StudentResponse.class);
    }

    // Lay diem thi cao nhat cua tung mon roi tinh gpa
    public Float updateStudentGpa(Long studentId) throws ObjectNotFoundException {
        List<ExamResult> exams = examResultRepository.findByStudentId(studentId);

        if (exams.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy điểm thi của sinh viên có id " + studentId);
        }

        Set<Long> subjectIds = new HashSet<>();
        for (int i = 0; i < exams.size(); i++) {
            subjectIds.add(exams.get(i).getSubject().getId());
        }

        List<ExamResult> finalResult = new ArrayList<>();

        subjectIds.forEach(subjectId -> {
            // Đoạn code này lọc ra danh sách các kết quả thi (ExamResult) cho từng môn học
            List<ExamResult> tempExams = exams
                    .stream()
                    .filter(e -> Objects.equals(e.getSubject().getId() , subjectId))
                    .toList();
            // Lay ra lan thi co diem cao nhat

            ExamResult max = tempExams.get(0);
            if(tempExams.size() == 1){
                finalResult.add(max);
                return;
            }

            for (int i = 1; i < tempExams.size(); i++) {
                if(tempExams.get(i).getMark() > max.getMark()){
                    max = tempExams.get(i);
                }
            }
            finalResult.add(max);
        });
        float tuSo = 0f;
        int mauSo = 0;
        for (int i = 0; i < finalResult.size(); i++) {
            ExamResult result = finalResult.get(i);
            tuSo+= result.getMark()*result.getSubject().getCredit();
            mauSo+=result.getSubject().getCredit();
        }
        if(mauSo == 0){
            return 0f;
        }
        return tuSo/mauSo;
    }

//    public Float updateStudentGpaVer2(Long studentId){
//        float gpa = 0f;
//        String sql= "";
//        return gpa;
//    }
}
