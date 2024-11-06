package com.example.homeworkemail01.service;

import com.example.homeworkemail01.constant.AccountStatus;
import com.example.homeworkemail01.entity.Account;
import com.example.homeworkemail01.exception.EmailExistedException;
import com.example.homeworkemail01.exception.ObjectNotFoundException;
import com.example.homeworkemail01.exception.UnprocessableEntityException;
import com.example.homeworkemail01.model.request.AccountRequest;
import com.example.homeworkemail01.model.response.AccountResponse;
import com.example.homeworkemail01.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountService {

    final EmailService emailService;

    final AccountRepository accountRepository;

    final ObjectMapper objectMapper;

    @Value("${application.accounts.activationMailExpiredInMilliseconds}")
    Long activationMailExpiredInMilliseconds;

    public AccountResponse registerAccount(AccountRequest request) throws MessagingException, EmailExistedException {
        // co email chua????
//        List<Account> accountList = accountRepository.findAll();
//        for (Account account : accountList) {
//            if (account.getEmail().equals(request.getEmail()) && account.getStatus().equals(AccountStatus.ACTIVE)) {
//                throw new EmailExistedException("Email đã được đăng ký");
//            }
//        }

        Optional<Account> accountOptional = accountRepository.findByEmailAndStatus(request.getEmail(), AccountStatus.ACTIVE);
        if (accountOptional.isPresent()) {
            throw new EmailExistedException("Email đã được đăng ký");
        }

        // b1: di tao account moi va luu vao DB (trang thai cua account la CHUA KICH HOAT)
        Account account = objectMapper.convertValue(request, Account.class);
        account.setStatus(AccountStatus.INACTIVE);
        account.setActivationMailSentAt(LocalDateTime.now());
        accountRepository.save(account);

        // b2: xac thuc email de kich hoat tai khoan --> gui mail
        emailService.sendEmailToActiveAccount(request.getEmail(), account.getId());

        return objectMapper.convertValue(account, AccountResponse.class);
    }

    public AccountResponse changeStatus(Long idAccount) throws ObjectNotFoundException, UnprocessableEntityException {
        Optional<Account> accountOptional = accountRepository.findById(idAccount);
        if (accountOptional.isEmpty()) {
            throw new ObjectNotFoundException("Khong tim thay account co id : " + idAccount);
        }
//        Account account = objectMapper.convertValue(accountOptional , Account.class);
        Account account = accountOptional.get();

        // check xem active tai khoan so voi thoi diem gui mail qua 15p chua?
        LocalDateTime now = LocalDateTime.now(); // thoi diem hien tai
        LocalDateTime activationMailSentAt = account.getActivationMailSentAt();

        Long nowInMilisecond = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long activationMailSentAtInMilisecond = activationMailSentAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        if (nowInMilisecond - activationMailSentAtInMilisecond > activationMailExpiredInMilliseconds) {
            throw new UnprocessableEntityException("Link kich hoat da het han, vui long lay lai link");
        }

        account.setStatus(AccountStatus.ACTIVE);
        return objectMapper.convertValue(account, AccountResponse.class);
    }
}
