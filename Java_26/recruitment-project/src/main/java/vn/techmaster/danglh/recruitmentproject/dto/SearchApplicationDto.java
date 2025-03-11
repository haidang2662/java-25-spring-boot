package vn.techmaster.danglh.recruitmentproject.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.ApplicationStatus;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchApplicationDto {

    Long id;

    String jobName;
    String candidateName;
    LocalDate appliedDate;
    ApplicationStatus status;

    String cvName;
    String cvUrl;

    Long totalRecord;

    Long cvId;
}
