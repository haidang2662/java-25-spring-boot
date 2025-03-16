package vn.techmaster.danglh.recruitmentproject.resource;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.request.InterviewRequest;
import vn.techmaster.danglh.recruitmentproject.model.request.SearchInterviewRequest;
import vn.techmaster.danglh.recruitmentproject.model.response.CommonSearchResponse;
import vn.techmaster.danglh.recruitmentproject.model.response.InterviewResponse;
import vn.techmaster.danglh.recruitmentproject.service.InterviewService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/interviews")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InterviewResource {

    InterviewService interviewService;

    @PostMapping
    public InterviewResponse createInterview(@RequestBody @Valid InterviewRequest request) throws ObjectNotFoundException, MessagingException {
        return interviewService.createInterview(request);
    }

    @GetMapping
    public CommonSearchResponse<?> getInterviews(SearchInterviewRequest request) {
        return interviewService.getInterviews(request);
    }

}
