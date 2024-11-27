package vn.techmaster.danglh.recruitmentproject.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import vn.techmaster.danglh.recruitmentproject.constant.AccountStatus;
import vn.techmaster.danglh.recruitmentproject.constant.RegistrationType;
import vn.techmaster.danglh.recruitmentproject.constant.Role;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {

    Long id;

    String email;

    String name;

    String headQuarterAddress;

    Integer employeeQuantity;

    String website;

//    RegistrationType type;

    Role role;

    AccountStatus status;

}
