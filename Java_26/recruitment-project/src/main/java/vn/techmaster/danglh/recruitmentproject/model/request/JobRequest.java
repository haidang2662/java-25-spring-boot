package vn.techmaster.danglh.recruitmentproject.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.JobLevel;
import vn.techmaster.danglh.recruitmentproject.constant.Literacy;
import vn.techmaster.danglh.recruitmentproject.constant.WorkingTimeType;
import vn.techmaster.danglh.recruitmentproject.constant.WorkingType;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobRequest {

    String name;
    String position;
    Integer yearOfExperienceFrom;
    Integer yearOfExperienceTo;

    WorkingType workingType;

    WorkingTimeType workingTimeType;

    String workingAddress;

    Literacy literacy;

    JobLevel level;

    Integer recruitingQuantity;
    LocalDate expiredDate;
    String skills;
    String benefit;
    String requirement;
    Integer salaryFrom;
    Integer salaryTo;


    String description;

}
