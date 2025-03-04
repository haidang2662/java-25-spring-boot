package vn.techmaster.danglh.recruitmentproject.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.danglh.recruitmentproject.exception.ExistedJobApplicationException;
import vn.techmaster.danglh.recruitmentproject.exception.InvalidFileExtensionException;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.request.JobApplicationRequest;
import vn.techmaster.danglh.recruitmentproject.model.response.ApplicationResponse;
import vn.techmaster.danglh.recruitmentproject.service.ApplicationService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/application")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationResource {

    ObjectMapper objectMapper;
    ApplicationService applicationService;

    @PostMapping
    public ApplicationResponse applyJob(
            @RequestPart("applicationRequest") String request,
            @RequestPart(value = "uploadedCv", required = false) MultipartFile uploadedCv
    ) throws InvalidFileExtensionException, ObjectNotFoundException, IOException, ExistedJobApplicationException {
        JobApplicationRequest requestObj = objectMapper.readValue(request, JobApplicationRequest.class);
        return applicationService.applyJob(requestObj, uploadedCv);
    }

}
