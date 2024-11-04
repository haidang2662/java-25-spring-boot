package com.example.manage_student_swagger.controller;

import com.example.manage_student_swagger.model.response.SubjectResponse;
import com.example.manage_student_swagger.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {

    SubjectService subjectService;

    @GetMapping
    public String getSubjects(Model model){

        List<SubjectResponse> subjectResponses = subjectService.getSubjects();
        model.addAttribute("subjects" , subjectResponses);
        return "/subject/subjects";
    }
}
