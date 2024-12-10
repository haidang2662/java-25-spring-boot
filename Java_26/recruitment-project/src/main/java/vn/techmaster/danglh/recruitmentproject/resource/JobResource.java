package vn.techmaster.danglh.recruitmentproject.resource;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
