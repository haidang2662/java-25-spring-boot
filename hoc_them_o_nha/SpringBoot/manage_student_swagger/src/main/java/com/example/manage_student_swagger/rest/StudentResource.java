package com.example.manage_student_swagger.rest;

import com.example.manage_student_swagger.exceptionHandling.ObjectNotFoundException;
import com.example.manage_student_swagger.model.request.SearchStudentRequest;
import com.example.manage_student_swagger.model.request.StudentRequest;
import com.example.manage_student_swagger.model.response.CommonSearchResponse;
import com.example.manage_student_swagger.model.response.StudentResponse;
import com.example.manage_student_swagger.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentResource {

    StudentService studentService;

    @GetMapping
    public CommonSearchResponse<StudentResponse> searchStudent(SearchStudentRequest request) {
        return studentService.searchStudent(request);
    }

    @PostMapping
    public StudentResponse createStudent(@RequestBody @Valid StudentRequest request){
        StudentResponse studentResponse = studentService.createStudent(request);
        return studentResponse;
    }

    @PutMapping("{id}")
    public StudentResponse updateStudent(@RequestBody StudentRequest request , @PathVariable("id") Long studentId) throws ObjectNotFoundException {
        StudentResponse studentResponse = studentService.updateStudent(request , studentId);
        return studentResponse;
    };

    @GetMapping("{id}")
    public StudentResponse getStudentDetails(@PathVariable("id") Long studentId) throws ObjectNotFoundException {
        StudentResponse studentResponse = studentService.getStudentDetails(studentId);
        return studentResponse;
    }
}
