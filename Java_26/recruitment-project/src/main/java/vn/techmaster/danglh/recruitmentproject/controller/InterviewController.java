package vn.techmaster.danglh.recruitmentproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companies/interviews")
public class InterviewController {

    @GetMapping
    public String creatInterview() {
        return "company/interview/interview-creation";
    }

}
