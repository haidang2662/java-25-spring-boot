package com.example.manage_student_swagger.service;

import com.example.manage_student_swagger.entity.FinalScore;
import com.example.manage_student_swagger.entity.Student;
import com.example.manage_student_swagger.entity.Subject;
import com.example.manage_student_swagger.exceptionHandling.ObjectNotFoundException;
import com.example.manage_student_swagger.exceptionHandling.UnprocessableEntityException;
import com.example.manage_student_swagger.model.request.FinalScoreRequest;
import com.example.manage_student_swagger.model.response.FinalScoreResponse;
import com.example.manage_student_swagger.model.response.StudentResponse;
import com.example.manage_student_swagger.model.response.SubjectResponse;
import com.example.manage_student_swagger.repository.FinalScoreCustomRepository;
import com.example.manage_student_swagger.repository.FinalScoreRepository;
import com.example.manage_student_swagger.repository.StudentRepository;
import com.example.manage_student_swagger.repository.SubjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FinalScoreService {

    FinalScoreRepository finalScoreRepository;

    StudentRepository studentRepository;

    SubjectRepository subjectRepository;

    ObjectMapper objectMapper;

    StudentService studentService;

    FinalScoreCustomRepository finalScoreCustomRepository;

    public List<FinalScoreResponse> getFinalScores() {
        List<FinalScore> finalScoreList = finalScoreRepository.findAll();
        return finalScoreList
                .stream()
                .map(s -> objectMapper.convertValue(s , FinalScoreResponse.class))
                .toList();
    }

    public FinalScoreResponse getFinalScoreDetails(Long idFinalScore) throws ObjectNotFoundException {
        Optional<FinalScore> finalScoreOptional = finalScoreRepository.findById(idFinalScore);
        if(finalScoreOptional.isEmpty()){
            throw new ObjectNotFoundException("Không tìm thấy điểm tổng kết có id là" +idFinalScore);
        }
        FinalScore finalScore = finalScoreOptional.get();
        return objectMapper.convertValue(finalScore , FinalScoreResponse.class);
    }

    @Transactional
    public FinalScoreResponse createFinalScore(FinalScoreRequest request) throws ObjectNotFoundException, UnprocessableEntityException {
        Optional<Student> studentOptional = studentRepository.findById(request.getStudentId());
        if(studentOptional.isEmpty()){
            throw new ObjectNotFoundException("Không tìm thấy sinh viên có id là : " +request.getSubjectId());
        }
        Student student = studentOptional.get();
        Optional<Subject> subjectOptional = subjectRepository.findById(request.getSubjectId());
        if(subjectOptional.isEmpty()){
            throw new ObjectNotFoundException("Không tìm thấy môn học có id là : " +request.getSubjectId());
        }
        Subject subject = subjectOptional.get();

        //check lan thi :

        List<FinalScore> finalScoreList = finalScoreRepository.findByStudentIdAndSubjectId(request.getStudentId() , request.getStudentId());
        if(finalScoreList.size() >= 3){
            throw new UnprocessableEntityException("Sinh vien khong duoc thi qua 3 lan");
        }
        FinalScore finalScore = FinalScore.builder()
                .student(student)
                .subject(subject)
                .scoreSubject(request.getScoreSubject())
                .examDay(request.getExamDay())
                .examTimes(finalScoreList.size() + 1)
                .build();
        finalScoreRepository.save(finalScore);
//        C1:
//        Float gpa = studentService.updateStudentScoreSubject(finalScore.getStudent().getId());
//        C2:
        Double gpa = finalScoreCustomRepository.calculateStudentGpa(request.getStudentId());
        student.setGpa(gpa.floatValue());
        studentRepository.save(student);
        return FinalScoreResponse.builder()
                .id(finalScore.getId())
                .studentResponse(objectMapper.convertValue(student , StudentResponse.class))
                .subjectResponse(objectMapper.convertValue(subject , SubjectResponse.class))
                .scoreSubject(finalScore.getScoreSubject())
                .examDay(finalScore.getExamDay())
                .examTimes(finalScore.getExamTimes())
                .build();
    }

    public FinalScoreResponse updateFinalScore(Long idFinalScore, FinalScoreRequest request) throws ObjectNotFoundException {
        Optional<FinalScore> finalScoreOptional = finalScoreRepository.findById(idFinalScore);
        if(finalScoreOptional.isEmpty()){
            throw new ObjectNotFoundException("Không tìm thấy điểm tổng kết có id là : " +idFinalScore);
        }
        FinalScore finalScore = finalScoreOptional.get();

        Optional<Student> studentOptional = studentRepository.findById(request.getStudentId());
        if (studentOptional.isEmpty()){
            throw new ObjectNotFoundException("Không tìm thấy sinh viên có id là " + request.getStudentId());
        }
        Student student = studentOptional.get();

        Optional<Subject> subjectOptional = subjectRepository.findById(request.getSubjectId());
        if(subjectOptional.isEmpty()){
            throw new ObjectNotFoundException("Không tìm thấy môn học có id là : " + request.getSubjectId());
        }
        Subject subject = subjectOptional.get();

        finalScore.setStudent(student);
        finalScore.setSubject(subject);
        finalScore.setExamDay(request.getExamDay());
        finalScore.setScoreSubject(request.getScoreSubject());
        finalScoreRepository.save(finalScore);
        return objectMapper.convertValue(finalScore , FinalScoreResponse.class);
    }
}
