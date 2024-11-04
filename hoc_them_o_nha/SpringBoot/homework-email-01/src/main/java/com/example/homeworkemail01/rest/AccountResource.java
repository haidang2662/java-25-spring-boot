package com.example.homeworkemail01.rest;

import com.example.homeworkemail01.exception.ObjectNotFoundException;
import com.example.homeworkemail01.model.request.AccountRequest;
import com.example.homeworkemail01.model.response.AccountResponse;
import com.example.homeworkemail01.service.AccountService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountResource {

    AccountService accountService;

    @PostMapping
    public AccountResponse registerAccount(@RequestBody @Valid AccountRequest request) throws MessagingException {
        return accountService.registerAccount(request);
    }

    @PutMapping("{idAccount}/activation")
    public AccountResponse changeStatusAccount(@PathVariable Long idAccount) throws ObjectNotFoundException {
        return accountService.changeStatus(idAccount);
    }

}
