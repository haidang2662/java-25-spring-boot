package com.example.homeworkemail01.controller;

import com.example.homeworkemail01.model.request.AccountRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("accountRequest") AccountRequest request) {
//        User user = authenticationService.login(request);
//        if (user == null) {
//            // sai email hoặc pass
//        }
//        model.addAttribute("loggedInUser", user);
//        return "home";
        return null;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("accountRequest", new AccountRequest());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/{id}/activation")
    public String activateAccount(@PathVariable Long id, Model model) {
        model.addAttribute("accountId", id);
        return "account-activation";
    }

    @GetMapping("/activation-success")
    public String activateAccountSuccessfully() {
        return "account-activation-success";
    }

    @GetMapping("/activation-failed")
    public String activateAccountFailed(@RequestParam("errorCode") String errorCode, Model model) {
        model.addAttribute("errorCode", errorCode);
        return "account-activation-failed";
    }


}
