package vn.techmaster.danglh.recruitmentproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import vn.techmaster.danglh.recruitmentproject.constant.Role;
import vn.techmaster.danglh.recruitmentproject.dto.SearchUserDto;
import vn.techmaster.danglh.recruitmentproject.entity.Account;
import vn.techmaster.danglh.recruitmentproject.exception.ExistedUserException;
import vn.techmaster.danglh.recruitmentproject.exception.ObjectNotFoundException;
import vn.techmaster.danglh.recruitmentproject.model.request.CreateAccountRequest;
import vn.techmaster.danglh.recruitmentproject.model.request.AccountSearchRequest;
import vn.techmaster.danglh.recruitmentproject.model.response.CommonSearchResponse;
import vn.techmaster.danglh.recruitmentproject.model.response.AccountResponse;
import vn.techmaster.danglh.recruitmentproject.model.response.AccountSearchResponse;
import vn.techmaster.danglh.recruitmentproject.repository.AccountRepository;
import vn.techmaster.danglh.recruitmentproject.repository.custom.UserCustomRepository;
import vn.techmaster.danglh.recruitmentproject.constant.AccountStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {

    final PasswordEncoder passwordEncoder;

    final AccountRepository accountRepository;


    final ObjectMapper objectMapper;

    final UserCustomRepository userCustomRepository;

    public AccountResponse getDetail(Long id) throws ObjectNotFoundException {
        return accountRepository.findById(id)
                .map(u -> objectMapper.convertValue(u, AccountResponse.class))
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }


    public AccountResponse createUser(CreateAccountRequest request) throws ExistedUserException {
        Optional<Account> userOptional = accountRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            throw new ExistedUserException("Username existed");
        }


        Account account = Account.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode("123")) // TODO: change to random password
                .role(Role.CANDIDATE) // TODO : Đoạn này chưa hiểu lắm làm thế nào để chọn candidate hay company
                .status(AccountStatus.ACTIVE)
                .build();
        accountRepository.save(account);
        return objectMapper.convertValue(account, AccountResponse.class);
    }

    public CommonSearchResponse<AccountSearchResponse> searchUser(AccountSearchRequest request) {
        List<SearchUserDto> result = userCustomRepository.searchUser(request);

        Long totalRecord = 0L;
        List<AccountSearchResponse> studentResponses = new ArrayList<>();
        if (!result.isEmpty()) {
            totalRecord = result.get(0).getTotalRecord();
            studentResponses = result
                    .stream()
                    .map(s -> objectMapper.convertValue(s, AccountSearchResponse.class))
                    .toList();
        }

        int totalPage = (int) Math.ceil((double) totalRecord / request.getPageSize());

        return CommonSearchResponse.<AccountSearchResponse>builder()
                .totalRecord(totalRecord)
                .totalPage(totalPage)
                .data(studentResponses)
                .pageInfo(new CommonSearchResponse.CommonPagingResponse(request.getPageSize(), request.getPageIndex()))
                .build();
    }

}
