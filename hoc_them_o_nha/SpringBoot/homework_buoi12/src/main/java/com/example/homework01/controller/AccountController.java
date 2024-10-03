package com.example.homework01.controller;

import com.example.homework01.EmailExistedException;
import com.example.homework01.entity.User;
import com.example.homework01.model.request.LoginRequest;
import com.example.homework01.model.request.RegisterRequest;
import com.example.homework01.model.request.UpdateUserRequest;
import com.example.homework01.model.response.UserResponse;
import com.example.homework01.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/accounts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {

    UserService userService;

    // validate dữ liệu

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest()); // Khởi tạo đối tượng User và đưa vào model
        return "register"; // Trả về trang đăng ký
    }

    @PostMapping("/register")
    public String processRegisterForm(@ModelAttribute @Valid RegisterRequest registerRequest, Errors errors)
            throws EmailExistedException {
        if (null == errors || errors.getErrorCount() <= 0) {
            // Lưu người dùng vào danh sách người dùng đã đăng ký
            userService.register(registerRequest);
//            model.addAttribute("message", "Đăng ký thành công!");
            return "redirect:/accounts/register-redirection";
        }
        return "redirect:/accounts";
    }

    @GetMapping("/register-redirection")
    public String redirectRegister(Model model) {
        model.addAttribute("message", "Đăng ký thành công!"); // Khởi tạo đối tượng User và đưa vào model
        model.addAttribute("registerRequest", new RegisterRequest()); // Khởi tạo đối tượng User và đưa vào model
        return "register"; // Trả về trang đăng ký
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest()); // Tạo đối tượng user để liên kết dữ liệu với form đăng nhập
        return "login"; // Trả về trang login
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute LoginRequest loginRequest, Model model) {

        // Kiểm tra xem người dùng đã đăng ký hay chưa
        User loggedInUser = userService.login(loginRequest);
        if (loggedInUser == null) {
            // Nếu thông tin đăng nhập không chính xác
            model.addAttribute("error", "Thông tin đăng nhập không chính xác!");
            return "login"; // Trả về trang login với thông báo lỗi
        }
        model.addAttribute("name", loggedInUser.getName());
        return "home";
    }

    @GetMapping()
    public String getUsers(Model model) {
        List<UserResponse> userModels = userService.findAllUsers();
        model.addAttribute("danhSachUser", userModels);
        return "user-list";
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
        return "update-user";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("userToiMuonCapNhat") @Valid UpdateUserRequest updateUserRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update-user";
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
