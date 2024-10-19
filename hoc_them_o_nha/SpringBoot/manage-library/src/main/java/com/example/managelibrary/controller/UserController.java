package com.example.managelibrary.controller;

import com.example.managelibrary.exceptionHandling.EmailExistedException;
import com.example.managelibrary.model.request.RegisterRequest;
import com.example.managelibrary.model.request.UpdateUserRequest;
import com.example.managelibrary.model.response.UserResponse;
import com.example.managelibrary.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@RequestMapping("/accounts")

public class UserController {

    UserService userService;

//    @GetMapping("/register")
//    public String showRegisterForm(Model model){
//        model.addAttribute("registerRequest" , new RegisterRequest());
//        return "reader/register";
//    }

    @PostMapping("/register")
    public String processRegisterForm(@ModelAttribute @Valid RegisterRequest registerRequest, Errors errors)
            throws EmailExistedException {
        if (null == errors || errors.getErrorCount() <= 0) {
            // Lưu người dùng vào danh sách người dùng đã đăng ký
            userService.register(registerRequest);
            return "redirect:/accounts";
        }
        return "redirect:/accounts/register-redirection";
    }

    @GetMapping("/register-redirection")
    public String redirectRegister(Model model) {
        model.addAttribute("message", "Đăng ký chưa thành công!");
        model.addAttribute("registerRequest", new RegisterRequest()); // Khởi tạo đối tượng User và đưa vào model
        return "reader/register"; // Trả về trang đăng ký
    }

    @GetMapping
    public String getUsers(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "6") int pageSize
    ) {
        Page<UserResponse> userModels = userService.findAllUsers(page , pageSize);
        model.addAttribute("danhSachUser", userModels);
        model.addAttribute("currentPage", page);
        model.addAttribute("registerRequest" , new RegisterRequest());


        return "reader/user-list";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteByIdUser(id);
        return "redirect:/accounts";
    }

    @GetMapping("/{id}/edit")
    public String forwardToUpdateForm(@PathVariable("id") Long id, Model model) {
        UpdateUserRequest updateUser = userService.findUserById(id);
        model.addAttribute("userToiMuonCapNhat", updateUser);
        return "reader/update-user";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("userToiMuonCapNhat") @Valid UpdateUserRequest updateUserRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "reader/update-user";
        }
        userService.updateUser(updateUserRequest);
        return "redirect:/accounts";
    }

    @GetMapping("/{id}/activations")
    public String changeUserActivation(@PathVariable("id") Long id, Model model) {
        userService.changeUserActivation(id);
        return "redirect:/accounts";
    }
}
