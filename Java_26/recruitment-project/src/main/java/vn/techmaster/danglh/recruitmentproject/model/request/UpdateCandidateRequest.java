package vn.techmaster.danglh.recruitmentproject.model.request;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.Gender;
import vn.techmaster.danglh.recruitmentproject.constant.Literacy;
import vn.techmaster.danglh.recruitmentproject.constant.WorkingTimeType;
import vn.techmaster.danglh.recruitmentproject.constant.WorkingType;
import vn.techmaster.danglh.recruitmentproject.entity.Account;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCandidateRequest {


    String name;

    LocalDate dob;

    Gender gender;

    String currentJobPosition;

    String phone;
    String address;
    String avatar;
    String skills;
    Double yearOfExperience;

    Literacy literacy;

    String graduatedAt;
    Integer expectedSalaryFrom;
    Integer expectedSalaryTo;

    WorkingTimeType expectedWorkingTimeType;

    WorkingType expectedWorkingType;

    public UpdateCandidateRequest(String name, LocalDate dob, Gender gender, String currentJobPosition, String phone, String address, String skills, Double yearOfExperience, Literacy literacy, String graduatedAt, Integer expectedSalaryFrom, Integer expectedSalaryTo, WorkingTimeType expectedWorkingTimeType, WorkingType expectedWorkingType) {

    }
}



