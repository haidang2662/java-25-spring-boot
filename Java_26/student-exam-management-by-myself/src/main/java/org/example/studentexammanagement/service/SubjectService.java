package org.example.studentexammanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.example.studentexammanagement.entity.Subject;
import org.example.studentexammanagement.model.request.SubjectRequest;
import org.example.studentexammanagement.model.response.SubjectResponse;
import org.example.studentexammanagement.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubjectService {

    ObjectMapper objectMapper;
    SubjectRepository subjectRepository;
    public SubjectResponse createSubject(SubjectRequest request) {

        Subject subject = objectMapper.convertValue(request, Subject.class);
        subjectRepository.save(subject);
        return objectMapper.convertValue(subject , SubjectResponse.class);
    }
}
