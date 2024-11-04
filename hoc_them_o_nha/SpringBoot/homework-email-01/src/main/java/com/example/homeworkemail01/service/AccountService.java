package com.example.homeworkemail01.service;

import com.example.homeworkemail01.constant.AccountStatus;
import com.example.homeworkemail01.entity.Account;
import com.example.homeworkemail01.exception.ObjectNotFoundException;
import com.example.homeworkemail01.model.request.AccountRequest;
import com.example.homeworkemail01.model.response.AccountResponse;
import com.example.homeworkemail01.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {

    EmailService emailService;

    AccountRepository accountRepository;

    ObjectMapper objectMapper;

    public AccountResponse registerAccount(AccountRequest request) throws MessagingException {
        // co email chua????


        // b1: di tao account moi va luu vao DB (trang thai cua account la CHUA KICH HOAT)
        Account account = objectMapper.convertValue(request, Account.class);
        accountRepository.save(account);

        // b2: xac thuc email de kich hoat tai khoan --> gui mail
        emailService.sendEmailToActiveAccount(request.getEmail(), account.getId());

        return objectMapper.convertValue(account, AccountResponse.class);
    }

    public AccountResponse changeStatus(Long idAccount) throws ObjectNotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(idAccount);
        if (accountOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay account co id : " + idAccount);
        }
//        Account account = objectMapper.convertValue(accountOptional , Account.class);
        Account account = accountOptional.get();
        account.setStatus(AccountStatus.ACTIVE);
        return objectMapper.convertValue(account, AccountResponse.class);
    }
}
