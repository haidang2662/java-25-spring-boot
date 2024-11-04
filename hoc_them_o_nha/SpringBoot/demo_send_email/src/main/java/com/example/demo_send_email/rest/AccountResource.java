package com.example.demo_send_email.rest;

import com.example.demo_send_email.model.request.RegistrationRequest;
import com.example.demo_send_email.model.response.RegistrationResponse;
import com.example.demo_send_email.service.AccountService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountResource {

    AccountService accountService;

    @PostMapping
    public RegistrationResponse registerAccount(@RequestBody @Valid RegistrationRequest request) {
        return accountService.registerAccount(request);
    }


}
