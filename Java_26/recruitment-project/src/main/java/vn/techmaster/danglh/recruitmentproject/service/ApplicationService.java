package vn.techmaster.danglh.recruitmentproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.danglh.recruitmentproject.constant.ApplicationStatus;
import vn.techmaster.danglh.recruitmentproject.constant.Constant;
import vn.techmaster.danglh.recruitmentproject.constant.Role;
import vn.techmaster.danglh.recruitmentproject.dto.SearchJobDto;
import vn.techmaster.danglh.recruitmentproject.entity.*;
import vn.techmaster.danglh.recruitmentproject.exception.ExistedJobApplicationException;
import vn.techmaster.danglh.recruitmentproject.exception.InvalidFileExtensionException;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.request.ApplicationSearchRequest;
import vn.techmaster.danglh.recruitmentproject.model.request.JobApplicationRequest;
import vn.techmaster.danglh.recruitmentproject.model.response.*;
import vn.techmaster.danglh.recruitmentproject.repository.*;
import vn.techmaster.danglh.recruitmentproject.security.CustomUserDetails;
import vn.techmaster.danglh.recruitmentproject.security.SecurityUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationService {

    FileService fileService;
    JobRepository jobRepository;
    CandidateCvRepository cvRepository;
    CandidateRepository candidateRepository;
    ApplicationRepository applicationRepository;
    AccountRepository accountRepository;
    ObjectMapper objectMapper;
    CandidateCvRepository candidateCvRepository;

    @Transactional
    public ApplicationResponse applyJob(JobApplicationRequest request, MultipartFile uploadedCv)
            throws InvalidFileExtensionException, ObjectNotFoundException, IOException, ExistedJobApplicationException {
        Long accountId = SecurityUtils.getCurrentUserLoginId()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ObjectNotFoundException("Account is not found"));
        Candidate candidate = candidateRepository.findByAccount(account)
                .orElseThrow(() -> new ObjectNotFoundException("Candidate not found"));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ObjectNotFoundException("Job not found"));

        Optional<Application> applicationOptional = applicationRepository.findFirstByCandidateAndJob(candidate, job);
        if (applicationOptional.isPresent()) {
            throw new ExistedJobApplicationException("Candidate had applied for this job already");
        }

        CandidateCv cv;
        if (request.getCvId() != null) {
            cv = cvRepository.findById(request.getCvId())
                    .orElseThrow(() -> new ObjectNotFoundException("Cv not found"));
        } else {
            if (!fileService.validateMultipartFile(uploadedCv, Constant.ALLOWED_FILE_EXTENSION.CV_FILE_EXTENSIONS)) {
                throw new InvalidFileExtensionException("CV file extension not allowed");
            }

            String fileName = fileService.saveFile(uploadedCv, CvService.CV_PATH);
            cv = new CandidateCv();
            cv.setCandidate(candidate);
            cv.setCvUrl(fileName);
            cv.setMain(false);
            candidateCvRepository.save(cv);
        }

        if (cv == null) {
            throw new FileNotFoundException("CV file invalid");
        }


        String applicationDescription = StringUtils.isBlank(request.getApplicationDescription()) ? null : request.getApplicationDescription();
        Application application = Application.builder()
                .job(job)
                .candidate(candidate)
                .cv(cv)
                .applicationDescription(applicationDescription)
                .status(ApplicationStatus.APPLIED)
                .build();
        applicationRepository.save(application);

        return ApplicationResponse.builder()
                .job(objectMapper.convertValue(job, JobResponse.class))
                .cv(objectMapper.convertValue(cv, CvResponse.class))
                .candidate(objectMapper.convertValue(candidate, CandidateResponse.class))
                .status(ApplicationStatus.APPLIED)
                .applicationDescription(applicationDescription)
                .build();
    }

//    public CommonSearchResponse<?> searchApplications(ApplicationSearchRequest request) {
//        Role role = null;
//        Long creatorId = null;
//        Long candidateId = null;
//        try {
//            CustomUserDetails authentication = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            role = authentication.getAccount().getRole();
//            if (role == Role.COMPANY) {
//                Optional<Company> companyOptional = companyRepository.findByAccount(authentication.getAccount());
//                if (companyOptional.isEmpty()) {
//                    return CommonSearchResponse.<JobSearchResponse>builder()
//                            .totalRecord(0L)
//                            .totalPage(1)
//                            .data(Collections.emptyList())
//                            .pageInfo(new CommonSearchResponse.CommonPagingResponse(request.getPageSize(), request.getPageIndex()))
//                            .build();
//                }
//                creatorId = companyOptional.get().getId();
//            }
//            if (role == Role.CANDIDATE) {
//                Optional<Candidate> candidateOptional = candidateRepository.findByAccount(authentication.getAccount());
//                if (candidateOptional.isEmpty()) {
//                    return CommonSearchResponse.<JobSearchResponse>builder()
//                            .totalRecord(0L)
//                            .totalPage(1)
//                            .data(Collections.emptyList())
//                            .pageInfo(new CommonSearchResponse.CommonPagingResponse(request.getPageSize(), request.getPageIndex()))
//                            .build();
//                }
//                candidateId = candidateOptional.get().getId();
//            }
//        } catch (Exception ignored) {
//            return null;
//        }
//        List<SearchJobDto> result = jobCustomRepository.searchJob(request, creatorId, candidateId, role);
//
//        Long totalRecord = 0L;
//        List<JobSearchResponse> jobResponses = new ArrayList<>();
//        if (!result.isEmpty()) {
//            totalRecord = result.get(0).getTotalRecord();
//            jobResponses = result
//                    .stream()
//                    .map(s -> {
//                        JobSearchResponse response = objectMapper.convertValue(s, JobSearchResponse.class);
//
//                        CompanyResponse companyResponse = new CompanyResponse();
//                        companyResponse.setName(s.getCompanyName());
//                        companyResponse.setAlias(s.getAlias());
//                        companyResponse.setEmail(s.getCompanyEmail());
//                        companyResponse.setHeadQuarterAddress(s.getHeadQuarterAddress());
//                        companyResponse.setWebsite(s.getWebsite());
//                        companyResponse.setCreatedAt(s.getCompanyCreatedAt());
//
//                        response.setCompany(companyResponse);
//                        return response;
//                    })
//                    .toList();
//        }
//
//        int totalPage = (int) Math.ceil((double) totalRecord / request.getPageSize());
//
//        return CommonSearchResponse.<JobSearchResponse>builder()
//                .totalRecord(totalRecord)
//                .totalPage(totalPage)
//                .data(jobResponses)
//                .pageInfo(new CommonSearchResponse.CommonPagingResponse(request.getPageSize(), request.getPageIndex()))
//                .build();
//    }
}
