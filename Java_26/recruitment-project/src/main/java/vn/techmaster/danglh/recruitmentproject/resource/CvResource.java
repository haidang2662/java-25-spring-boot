package vn.techmaster.danglh.recruitmentproject.resource;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.danglh.recruitmentproject.exception.InvalidFileExtensionException;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
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

}
