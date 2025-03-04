package vn.techmaster.danglh.recruitmentproject.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.ApplicationStatus;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationResponse {

    JobResponse job;

    CvResponse cv;

    CandidateResponse candidate;

    String applicationDescription;

    ApplicationStatus status;

    String recruiterComment;

}
