package vn.techmaster.danglh.recruitmentproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.danglh.recruitmentproject.constant.ApplicationStatus;
import vn.techmaster.danglh.recruitmentproject.constant.Constant;
import vn.techmaster.danglh.recruitmentproject.entity.*;
import vn.techmaster.danglh.recruitmentproject.exception.ExistedJobApplicationException;
import vn.techmaster.danglh.recruitmentproject.exception.InvalidFileExtensionException;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.request.JobApplicationRequest;
import vn.techmaster.danglh.recruitmentproject.model.response.ApplicationResponse;
import vn.techmaster.danglh.recruitmentproject.model.response.CandidateResponse;
import vn.techmaster.danglh.recruitmentproject.model.response.CvResponse;
import vn.techmaster.danglh.recruitmentproject.model.response.JobResponse;
import vn.techmaster.danglh.recruitmentproject.repository.*;
import vn.techmaster.danglh.recruitmentproject.security.SecurityUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
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
}
