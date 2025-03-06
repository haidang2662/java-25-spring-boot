package vn.techmaster.danglh.recruitmentproject.model.request;

import lombok.Data;
import vn.techmaster.danglh.recruitmentproject.constant.ApplicationStatus;

@Data
public class ApplicationSearchRequest {
    String jobName;
    String candidateName;
    ApplicationStatus status;
}
