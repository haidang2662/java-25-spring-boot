package vn.techmaster.danglh.recruitmentproject.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.danglh.recruitmentproject.constant.Constant;
import vn.techmaster.danglh.recruitmentproject.entity.Account;
import vn.techmaster.danglh.recruitmentproject.entity.Candidate;
import vn.techmaster.danglh.recruitmentproject.entity.CandidateCv;
import vn.techmaster.danglh.recruitmentproject.exception.InvalidFileExtensionException;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.response.CvResponse;
import vn.techmaster.danglh.recruitmentproject.repository.AccountRepository;
import vn.techmaster.danglh.recruitmentproject.repository.CandidateCvRepository;
import vn.techmaster.danglh.recruitmentproject.repository.CandidateRepository;
import vn.techmaster.danglh.recruitmentproject.security.SecurityUtils;

import java.io.File;
import java.io.IOException;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CvService {

    AccountRepository accountRepository;
    CandidateRepository candidateRepository;
    FileService fileService;
    CandidateCvRepository candidateCvRepository;

    static final String CV_PATH = System.getProperty("user.dir") + File.separator + Constant.FOLDER_NAME.FILE_FOLDER_NAME + File.separator + Constant.FOLDER_NAME.CV_FOLDER_NAME;

    public CvResponse uploadCv(MultipartFile cvFile) throws ObjectNotFoundException, IOException, InvalidFileExtensionException {
        if (!fileService.validateMultipartFile(cvFile, Constant.ALLOWED_FILE_EXTENSION.CV_FILE_EXTENSIONS)) {
            throw new InvalidFileExtensionException("CV file extension not allowed");
        }

        Long accountId = SecurityUtils.getCurrentUserLoginId()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ObjectNotFoundException("Account is not found"));
        Candidate candidate = candidateRepository.findByAccount(account)
                .orElseThrow(() -> new ObjectNotFoundException("Candidate not found"));

        String fileName = fileService.saveFile(cvFile, CV_PATH);

        CandidateCv cv = new CandidateCv();
        cv.setCandidate(candidate);
        cv.setCvUrl(fileName);
        cv.setMain(false);
        candidateCvRepository.save(cv);

        return CvResponse.builder()
                .id(cv.getId())
                .url(cv.getCvUrl())
                .main(cv.isMain())
                .build();
    }

}
