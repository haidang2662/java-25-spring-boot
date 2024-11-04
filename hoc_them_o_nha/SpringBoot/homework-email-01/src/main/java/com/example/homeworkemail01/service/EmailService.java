package com.example.homeworkemail01.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailService {

    final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String from;

    @Value("${application.accounts.activationUrl}")
    String activationAccountUrl;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmailToActiveAccount(String email, Long accountId) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

        String url = activationAccountUrl.replace("{id}", String.valueOf(accountId));
        System.out.println(url);
        String content = "<span>Welcome to shopee. Click <a href='" + url + "'>here</a> to active your account</span>";
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("[Shopee] Register account successfully");
        mimeMessageHelper.setText(content, true);

        javaMailSender.send(mimeMessage);
    }

}

