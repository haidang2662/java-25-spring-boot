package vn.techmaster.danglh.recruitmentproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companies/applications")
public class CompanyApplicationController {

    @GetMapping
    public String createJob() {
        return "/company/application/applications";
    }

}
