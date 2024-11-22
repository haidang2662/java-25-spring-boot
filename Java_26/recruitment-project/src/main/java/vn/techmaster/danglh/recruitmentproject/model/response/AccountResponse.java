package vn.techmaster.danglh.recruitmentproject.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.RegistrationType;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {

    Long id;

    String email;

    String name;

    String headQuarterAddress;

    Integer employeeQuantity;

    String website;

    RegistrationType type;
}
