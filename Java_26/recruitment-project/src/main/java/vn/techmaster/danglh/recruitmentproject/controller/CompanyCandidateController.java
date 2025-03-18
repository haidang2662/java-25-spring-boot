package vn.techmaster.danglh.recruitmentproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companies/candidates")
public class CompanyCandidateController {

    @GetMapping
    public String getInterviews() {
        return "company/candidate/candidates";
    }

}
