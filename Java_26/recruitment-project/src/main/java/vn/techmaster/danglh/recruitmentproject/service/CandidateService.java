package vn.techmaster.danglh.recruitmentproject.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.techmaster.danglh.recruitmentproject.model.response.CandidateResponse;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidateService {


    public CandidateResponse getDetails(Long id) {
        return null;
    }
}
