package vn.techmaster.danglh.recruitmentproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;
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

import java.util.List;

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

    public Page<JobResponse> getJobs(int page , int pageSize) {

        // 1 - tìm trong DB
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("id").descending());
        Page<Job> jobPage = jobRepository.findAll(pageable);

        // 2 - convert từ entity sang response
        List<Job> jobList = jobPage.getContent();

        // 2.4 - dùng object mapper kết hợp với stream của java 8
        List<JobResponse> jobResponses = jobList
                .stream()
                .map(jobTemp -> objectMapper.convertValue(jobTemp, JobResponse.class))
                .toList();

        // 3 - trả về kết quả
        return new PageImpl<>(jobResponses, pageable, jobPage.getTotalElements());

    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public JobResponse getJobDetails(Long idJob) throws ObjectNotFoundException {
        Job job = jobRepository.findById(idJob)
                .orElseThrow(() -> new ObjectNotFoundException("Không tìm thấy job có id : " +idJob));
        return objectMapper.convertValue(job , JobResponse.class);
    }

    public JobResponse updateJob(Long idJob, JobRequest request) throws ObjectNotFoundException {
        Job job = jobRepository.findById(idJob)
                .orElseThrow(() -> new ObjectNotFoundException("Không tìm thấy job có id : " +idJob));
        job.setName(request.getName());
        job.setPosition(request.getPosition());
        job.setYearOfExperienceFrom(request.getYearOfExperienceFrom());
        job.setYearOfExperienceTo(request.getYearOfExperienceTo());
        job.setWorkingType(request.getWorkingType());
        job.setWorkingTimeType(request.getWorkingTimeType());
        job.setWorkingAddress(request.getWorkingAddress());
        job.setLiteracy(request.getLiteracy());
        job.setLevel(request.getLevel());
        job.setExpiredDate(request.getExpiredDate());
        job.setSkills(request.getSkills());
        job.setDescription(request.getDescription());
        job.setBenefit(request.getBenefit());
        job.setRequirement(request.getRequirement());
        job.setSalaryFrom(request.getSalaryFrom());
        job.setSalaryTo(request.getSalaryTo());
        jobRepository.save(job);
        return objectMapper.convertValue(job , JobResponse.class);
    }
}
