package com.example.studentwebmanagement.service;

import com.example.studentwebmanagement.entity.Student;
import com.example.studentwebmanagement.model.request.StudentCreationRequest;
import com.example.studentwebmanagement.model.response.StudentResponse;
import com.example.studentwebmanagement.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    StudentRepository studentRepository;

    ObjectMapper objectMapper;

    public List<StudentResponse> getStudents() {
        List<Student> students = studentRepository.findAll();
        return students
                .stream()
                .map(s -> objectMapper.convertValue(s, StudentResponse.class))
                .toList();
    }

    public void create(StudentCreationRequest request) {
        Student student = objectMapper.convertValue(request, Student.class);
        studentRepository.save(student);
    }
}
