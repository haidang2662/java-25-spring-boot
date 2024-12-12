package vn.techmaster.danglh.recruitmentproject.resource;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.request.JobRequest;
import vn.techmaster.danglh.recruitmentproject.model.response.JobResponse;
import vn.techmaster.danglh.recruitmentproject.service.JobService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/jobs")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobResource {

    JobService jobService;

    @PostMapping
    public JobResponse postJob(@RequestBody @Valid JobRequest request) throws ObjectNotFoundException {
        return jobService.postJob(request);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
    }

    @GetMapping("/{id}")
    public JobResponse getJobDetails(@PathVariable("id") Long idJob) throws ObjectNotFoundException {
        return jobService.getJobDetails(idJob);
    }

    @PutMapping("{id}")
    public JobResponse updateJob(@PathVariable("id") Long idJob, @RequestBody JobRequest request) throws ObjectNotFoundException {
        return jobService.updateJob(idJob, request);
    }

}
