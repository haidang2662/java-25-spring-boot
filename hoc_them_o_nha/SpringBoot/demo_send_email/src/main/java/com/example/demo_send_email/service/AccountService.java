package com.example.demo_send_email.service;

import com.example.demo_send_email.model.request.RegistrationRequest;
import com.example.demo_send_email.model.response.RegistrationResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {

    EmailService emailService;

    public RegistrationResponse registerAccount(RegistrationRequest request) {
        // b1: di tao account moi va luu vao DB (trang thai cua account la CHUA KICH HOAT)
        System.out.println(123);

        // b2: xac thuc email de kich hoat tai khoan --> gui mail
        emailService.sendEmailToActiveAccount(request.getEmail());

        return new RegistrationResponse();
    }

}
