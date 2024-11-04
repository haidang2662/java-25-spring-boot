package com.example.demo_send_email.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailService {

    final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String from;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmailToActiveAccount(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        String content = "Welcome to shopee. Click <a href='localhost:8080/api/v1/accounts/7/activation'>here</a> to active your account";

        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("[Shopee] Register account successfully");
        simpleMailMessage.setText(content);

        javaMailSender.send(simpleMailMessage);
    }

}
