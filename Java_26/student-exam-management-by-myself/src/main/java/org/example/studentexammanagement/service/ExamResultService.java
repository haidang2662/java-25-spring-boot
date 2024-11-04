package org.example.studentexammanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.studentexammanagement.entity.ExamResult;
import org.example.studentexammanagement.entity.Student;
import org.example.studentexammanagement.entity.Subject;
import org.example.studentexammanagement.exception.ObjectNotFoundException;
import org.example.studentexammanagement.exception.UnprocessableEntityException;
import org.example.studentexammanagement.model.request.ExamResultRequest;
import org.example.studentexammanagement.model.response.ExamResultResponse;
import org.example.studentexammanagement.model.response.StudentResponse;
import org.example.studentexammanagement.model.response.SubjectResponse;
import org.example.studentexammanagement.repository.ExamResultCustomRepository;
import org.example.studentexammanagement.repository.ExamResultRepository;
import org.example.studentexammanagement.repository.StudentRepository;
import org.example.studentexammanagement.repository.SubjectRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExamResultService {

    ObjectMapper objectMapper;
    ExamResultRepository examResultRepository;
    StudentRepository studentRepository;
    SubjectRepository subjectRepository;
    StudentService studentService;
    ExamResultCustomRepository examResultCustomRepository;



    @Transactional
    public ExamResultResponse createExamResult(ExamResultRequest request) throws ObjectNotFoundException, UnprocessableEntityException {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ObjectNotFoundException("Student Not found"));

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new ObjectNotFoundException("Subject Not found"));
        //check lan thi :

        List<ExamResult> examResultList = examResultRepository.findByStudentIdAndSubjectId(request.getStudentId(), request.getSubjectId());
        if(examResultList.size() >= 3){
            throw new UnprocessableEntityException("Student can not take exam > 3 ");
        };

        ExamResult examResult = ExamResult.builder()
                .student(student)
                .subject(subject)
                .mark(request.getMark())
                .examDate(request.getExamDate())
                .examTimes(examResultList.size() + 1)
                .build();

        // Cach 1 : tinh diem trung binh va update bang student -> dung Async

        examResultRepository.save(examResult);
        Double gpa = examResultCustomRepository.calculateStudentGpa(request.getStudentId());
        student.setGpa(gpa.floatValue());
        studentRepository.save(student);

        return ExamResultResponse.builder()
                .id(examResult.getId())
                .studentResponse(objectMapper.convertValue(student , StudentResponse.class))
                .subjectResponse(objectMapper.convertValue(subject , SubjectResponse.class))
                .mark(examResult.getMark())
                .examDate(examResult.getExamDate())
                .examTimes(examResult.getExamTimes())
                .build();

        // Cach 2 : Tinh diem trung binh dinh ky
        // ==> Cron job (scheduler)
    }
}
