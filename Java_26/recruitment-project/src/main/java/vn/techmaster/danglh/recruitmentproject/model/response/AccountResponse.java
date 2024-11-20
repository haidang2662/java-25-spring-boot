package vn.techmaster.danglh.recruitmentproject.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {

    Long id;

    String email;

    String name;

}
