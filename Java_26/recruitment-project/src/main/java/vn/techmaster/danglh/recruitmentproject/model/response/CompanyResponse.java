package vn.techmaster.danglh.recruitmentproject.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyResponse {

    Long id;
    String name;
    String alias;
    String email;
    String phone;
    String headQuarterAddress;
    String website;
    LocalDate createdAt;
    String avatarUrl;
    String coverImageUrl;
    Integer employeeQuantity;

}
