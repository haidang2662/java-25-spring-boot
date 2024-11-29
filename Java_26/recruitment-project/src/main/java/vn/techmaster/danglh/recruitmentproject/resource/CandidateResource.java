package vn.techmaster.danglh.recruitmentproject.resource;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.danglh.recruitmentproject.model.response.CandidateResponse;
import vn.techmaster.danglh.recruitmentproject.service.CandidateService;

@RestController
@RequestMapping("/api/v1/candidates")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CandidateResource {

    CandidateService candidateService;

    @GetMapping("/{id}")
    public CandidateResponse getDetails(@PathVariable Long id){
        return candidateService.getDetails(id);
    }

}
