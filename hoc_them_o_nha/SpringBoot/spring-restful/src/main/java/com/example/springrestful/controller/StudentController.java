package com.example.springrestful.controller;

import com.example.springrestful.model.request.StudentCreationRequest;
import com.example.springrestful.model.response.StudentResponse;
import com.example.springrestful.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    StudentService studentService;

    @GetMapping("/creation-form")
    public String showCreationForm(Model model) {
        model.addAttribute("sinhVienTaoMoi", new StudentCreationRequest());
        return "student/creation-form";
    }

    @GetMapping
    public String listStudent(Model model) {
        List<StudentResponse> responses = studentService.getListStudent();
        model.addAttribute("students", responses);
        return "student/students";
    }

//    @GetMapping
//    public ResponseEntity<List<StudentResponse>> getListStudent() {
//        List<StudentResponse> data = studentService.getListStudent();
//        return ResponseEntity.ok(data);
//    }

}
