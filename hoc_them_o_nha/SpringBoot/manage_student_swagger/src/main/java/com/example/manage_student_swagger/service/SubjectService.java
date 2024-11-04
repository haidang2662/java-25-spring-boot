package com.example.manage_student_swagger.service;

import com.example.manage_student_swagger.entity.Subject;
import com.example.manage_student_swagger.exceptionHandling.ObjectNotFoundException;
import com.example.manage_student_swagger.model.request.SubjectRequest;
import com.example.manage_student_swagger.model.response.SubjectResponse;
import com.example.manage_student_swagger.repository.SubjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubjectService {

    SubjectRepository subjectRepository;

    ObjectMapper objectMapper;

    public List<SubjectResponse> getSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList
                .stream()
                .map(s -> objectMapper.convertValue(s, SubjectResponse.class))
                .toList();
    }

    public SubjectResponse getSubjectDeails(Long idSubject) throws ObjectNotFoundException {
        Optional<Subject> subjectOptional = subjectRepository.findById(idSubject);
        if(subjectOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay mon hoc co id " +idSubject);
        }
        Subject subject = subjectOptional.get();
        return objectMapper.convertValue(subject , SubjectResponse.class);
    }

    public SubjectResponse creatSubject(SubjectRequest request) {
        Subject subject = objectMapper.convertValue(request , Subject.class);
        subjectRepository.save(subject);
        return objectMapper.convertValue(subject , SubjectResponse.class);
    }

    public SubjectResponse updateSubject(Long idSubject, SubjectRequest request) throws ObjectNotFoundException {
        Optional<Subject> subjectOptional = subjectRepository.findById(idSubject);
        if(subjectOptional.isEmpty()){
            throw new ObjectNotFoundException("Không tìm thấy môn học có id " + idSubject);
        }

        Subject subject = subjectOptional.get();
        subject.setName(request.getName());
        subject.setTotalCredits(request.getTotalCredits());
        subjectRepository.save(subject);
        return objectMapper.convertValue(subject , SubjectResponse.class);
    }


    public void deleteSubject(Long idSubject) {
         subjectRepository.deleteById(idSubject);
    }
}
