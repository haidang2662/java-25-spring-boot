package vn.techmaster.danglh.recruitmentproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jobs")
public class CandidateJobController {

    @GetMapping
    public String searchJob() {
        return "candidate/job/jobs";
    }

    @GetMapping("/{id}")
    public String getJobDetails() {
        return "candidate/job/job-details";
    }

    @GetMapping("/{id}/application")
    public String applyJob() {
        return "candidate/job/job-application";
    }

    @GetMapping("/{id}/application/success")
    public String applyJobSuccess() {
        return "candidate/job/job-application-successfully";
    }

    @GetMapping("/favourite")
    public String listJobFavourite(){return "candidate/job/favourite-jobs";}

}
