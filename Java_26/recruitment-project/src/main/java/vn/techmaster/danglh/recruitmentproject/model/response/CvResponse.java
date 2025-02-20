package vn.techmaster.danglh.recruitmentproject.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CvResponse {

    private Long id;
    private String url;
    private boolean main;

}
