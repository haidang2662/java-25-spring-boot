package vn.techmaster.danglh.recruitmentproject.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.InterviewStatus;
import vn.techmaster.danglh.recruitmentproject.constant.InterviewType;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InterviewSearchResponse {

    Long id;
    String candidateName;
    String jobTitle;
    LocalDateTime interviewEmailSentAt;
    LocalDateTime interviewAt;
    InterviewType type;
    InterviewStatus status;

}
