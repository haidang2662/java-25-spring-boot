package vn.techmaster.danglh.recruitmentproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.techmaster.danglh.recruitmentproject.constant.JobStatus;
import vn.techmaster.danglh.recruitmentproject.entity.Company;
import vn.techmaster.danglh.recruitmentproject.entity.Job;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.request.JobRequest;
import vn.techmaster.danglh.recruitmentproject.model.response.JobResponse;
import vn.techmaster.danglh.recruitmentproject.repository.CompanyRepository;
import vn.techmaster.danglh.recruitmentproject.repository.JobRepository;
import vn.techmaster.danglh.recruitmentproject.security.CustomUserDetails;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class JobService {

    ObjectMapper objectMapper;

    JobRepository jobRepository;

    CompanyRepository companyRepository;

    public JobResponse postJob(JobRequest request) throws ObjectNotFoundException {
        Job job = objectMapper.convertValue(request, Job.class);
        job.setStatus(JobStatus.DRAFT);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Company company = companyRepository.findByAccount(userDetails.getAccount())
                .orElseThrow(() -> new ObjectNotFoundException("Company not found"));
        job.setCompany(company);
        jobRepository.save(job);
        return objectMapper.convertValue(job, JobResponse.class);
    }

}
