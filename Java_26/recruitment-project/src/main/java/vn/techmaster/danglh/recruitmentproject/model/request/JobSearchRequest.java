package vn.techmaster.danglh.recruitmentproject.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.techmaster.danglh.recruitmentproject.constant.JobLevel;
import vn.techmaster.danglh.recruitmentproject.constant.JobStatus;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class JobSearchRequest extends BaseSearchRequest {

    String name;

    String position;

    JobLevel level;

    String skills;

    LocalDate expiredDateFrom;

    LocalDate expiredDateTo;

    JobStatus status;

}
