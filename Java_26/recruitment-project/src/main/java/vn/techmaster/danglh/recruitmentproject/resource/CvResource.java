package vn.techmaster.danglh.recruitmentproject.resource;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.danglh.recruitmentproject.exception.InvalidFileExtensionException;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.request.BaseSearchRequest;
import vn.techmaster.danglh.recruitmentproject.model.request.JobSearchRequest;
import vn.techmaster.danglh.recruitmentproject.model.response.CommonSearchResponse;
import vn.techmaster.danglh.recruitmentproject.model.response.CvResponse;
import vn.techmaster.danglh.recruitmentproject.service.CvService;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cv")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CvResource {

    CvService cvService;

    @PostMapping
    public CvResponse uploadCv(@RequestPart(value = "cvFile") MultipartFile cvFile)
            throws ObjectNotFoundException, IOException, InvalidFileExtensionException {
        return cvService.uploadCv(cvFile);
    }

    @GetMapping
    public CommonSearchResponse<?> getListCv(BaseSearchRequest request) throws ObjectNotFoundException {
        return cvService.searchCv(request);
    }

}
