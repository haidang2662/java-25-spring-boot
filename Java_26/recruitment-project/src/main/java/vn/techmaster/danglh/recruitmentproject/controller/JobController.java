package vn.techmaster.danglh.recruitmentproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @GetMapping("/job-posting")
    public String postJob() {
        return "/company/post-job";
    }

    @GetMapping
    public String manageJob() {
        return "company/jobs";
    }
}
