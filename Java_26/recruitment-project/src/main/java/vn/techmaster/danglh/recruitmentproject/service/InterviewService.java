package vn.techmaster.danglh.recruitmentproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.techmaster.danglh.recruitmentproject.constant.ApplicationStatus;
import vn.techmaster.danglh.recruitmentproject.constant.InterviewStatus;
import vn.techmaster.danglh.recruitmentproject.constant.InterviewType;
import vn.techmaster.danglh.recruitmentproject.entity.Application;
import vn.techmaster.danglh.recruitmentproject.entity.Interview;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.request.InterviewRequest;
import vn.techmaster.danglh.recruitmentproject.model.response.InterviewResponse;
import vn.techmaster.danglh.recruitmentproject.repository.ApplicationRepository;
import vn.techmaster.danglh.recruitmentproject.repository.InterviewRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InterviewService {

    ApplicationRepository applicationRepository;
    InterviewRepository interviewRepository;
    ObjectMapper objectMapper;
    EmailService emailService;

    @Transactional
    public InterviewResponse createInterview(InterviewRequest request) throws ObjectNotFoundException, MessagingException {
        Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new ObjectNotFoundException("Application not found"));

        if (request.getInterviewType().equals(InterviewType.OFFLINE) && StringUtils.isBlank(request.getInterviewAddress())) {
            throw new IllegalArgumentException("Offline interview will require an address");
        }

        Interview interview = Interview.builder()
                .application(application)
                .interviewAt(request.getInterviewAt())
                .interviewType(request.getInterviewType())
                .interviewStep(1)
                .interviewAddress(request.getInterviewAddress())
                .status(InterviewStatus.CREATED)
                .invitationEmailSentAt(LocalDateTime.now())
                .build();
        interviewRepository.save(interview);

        application.setStatus(ApplicationStatus.WAIT_FOR_INTERVIEW);
        applicationRepository.save(application);

        emailService.sendNotifyToInterviewMail(application.getCandidate(), application.getJob().getCompany(), interview, application);

        return objectMapper.convertValue(interview, InterviewResponse.class);
    }
}
