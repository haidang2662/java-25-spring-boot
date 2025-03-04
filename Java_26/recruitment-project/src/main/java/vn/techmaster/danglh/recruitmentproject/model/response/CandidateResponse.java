package vn.techmaster.danglh.recruitmentproject.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.Gender;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidateResponse {

    Long id;

    String name;

    Gender gender;

}
