package vn.techmaster.danglh.recruitmentproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.techmaster.danglh.recruitmentproject.model.response.JobResponse;
import vn.techmaster.danglh.recruitmentproject.service.JobService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("companies/jobs")
public class CompanyJobController {

    JobService jobService;

    @GetMapping("/job-posting")
    public String postJob() {
        return "/company/job/post-job";
    }

    @GetMapping
    public String manageJob(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "6") int pageSize
    ) {
        Page<JobResponse> jobResponseList = jobService.getJobs(page , pageSize);
        model.addAttribute("jobs" , jobResponseList);
        model.addAttribute("currentPage", page);
        return "company/job/jobs";
    }
}
