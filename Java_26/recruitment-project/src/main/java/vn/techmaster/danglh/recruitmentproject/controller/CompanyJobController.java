package vn.techmaster.danglh.recruitmentproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.techmaster.danglh.recruitmentproject.service.JobService;

@Controller
@AllArgsConstructor
@RequestMapping("companies/jobs")
public class CompanyJobController {

    JobService jobService;

    @GetMapping("/job-posting")
    public String createJob() {
        return "/company/job/post-job";
    }

    @GetMapping
    public String searchJob() {
        return "company/job/jobs";
    }

    @GetMapping("/{id}")
    public String jobDetails() {
        return "company/job/job-details";
    }

    @GetMapping("/job-updating/{id}")
    public String updateJob() {
        return "/company/job/post-job";
    }

}
