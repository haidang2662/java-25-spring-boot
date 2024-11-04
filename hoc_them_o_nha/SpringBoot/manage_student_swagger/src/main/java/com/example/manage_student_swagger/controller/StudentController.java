package com.example.manage_student_swagger.controller;

import com.example.manage_student_swagger.entity.Student;
import com.example.manage_student_swagger.model.response.StudentResponse;
import com.example.manage_student_swagger.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    StudentService studentService;

    @GetMapping
    public String listStudent(Model model){
        List<StudentResponse> studentResponses = studentService.getStudents();
        model.addAttribute("students" , studentResponses);
        return "/student/students";
    }
}
