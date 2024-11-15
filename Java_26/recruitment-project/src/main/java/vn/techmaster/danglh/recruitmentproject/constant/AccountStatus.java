package vn.techmaster.danglh.recruitmentproject.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {

    CREATED,
    ACTIVATED,
    DEACTIVATED,
    LOCKED // or BANNED

}
