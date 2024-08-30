package com.example.demologinweb.controller;

import com.example.demologinweb.entity.User;
import com.example.demologinweb.model.LoginRequest;
import com.example.demologinweb.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("logInRequest") LoginRequest request) {
        User user = authenticationService.login(request);
        if (user == null) {
            // sai email hoặc pass
        }
        model.addAttribute("loggedInUser", user);
        return "home";
    }

    @GetMapping("/login-forward")
    public String loginForward(Model model) {
        model.addAttribute("logInRequest", new LoginRequest());
        return "loginPage";
    }

}
