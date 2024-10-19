package com.example.studentwebmanagement.controller;

import com.example.studentwebmanagement.model.request.StudentCreationRequest;
import com.example.studentwebmanagement.model.response.StudentResponse;
import com.example.studentwebmanagement.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    StudentService studentService;

    @GetMapping
    public String getStudents(Model model) {
        List<StudentResponse> students = studentService.getStudents();
        model.addAttribute("students", students);

        StudentCreationRequest request = new StudentCreationRequest();
        model.addAttribute("sinhVienTaoMoi", request);

        return "student/students";
    }

//    @GetMapping("/creation-form")
//    public String showCreationForm(Model model) {
//        StudentCreationRequest request = new StudentCreationRequest();
//        model.addAttribute("sinhVienTaoMoi", request);
//        return "student/student-creation";
//    }

    @PostMapping
    public String createStudent(@ModelAttribute("sinhVienTaoMoi") StudentCreationRequest request, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("sinhVienTaoMoi", request);
            return "student/student-creation";
        }
        studentService.create(request);
        return "redirect:/students";
    }


}
