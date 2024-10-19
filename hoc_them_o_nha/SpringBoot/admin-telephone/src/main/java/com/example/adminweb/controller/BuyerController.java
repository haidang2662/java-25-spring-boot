package com.example.adminweb.controller;

import com.example.adminweb.exceptionHandling.EmailExistedException;
import com.example.adminweb.model.request.RegisterRequest;
import com.example.adminweb.model.request.UpdateBuyerRequest;
import com.example.adminweb.model.response.BuyerResponse;
import com.example.adminweb.service.BuyerService;
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
@RequestMapping("/accounts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BuyerController {

    BuyerService buyerService;

    // validate dữ liệu

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest()); // Khởi tạo đối tượng User và đưa vào model
        return "buyer/register"; // Trả về trang đăng ký
    }

    @PostMapping("/register")
    public String processRegisterForm(@ModelAttribute @Valid RegisterRequest registerRequest, Errors errors)
            throws EmailExistedException {
        if (null == errors || errors.getErrorCount() <= 0) {
            // Lưu người dùng vào danh sách người dùng đã đăng ký
            buyerService.register(registerRequest);
//            model.addAttribute("message", "Đăng ký thành công!");
            return "redirect:/accounts/register-redirection";
        }
        return "redirect:/accounts";
    }

    @GetMapping("/register-redirection")
    public String redirectRegister(Model model) {
        model.addAttribute("message", "Đăng ký thành công!"); // Khởi tạo đối tượng User và đưa vào model
        model.addAttribute("registerRequest", new RegisterRequest()); // Khởi tạo đối tượng User và đưa vào model
        return "buyer/register"; // Trả về trang đăng ký
    }

    @GetMapping
    public String getUsers(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "6") int pageSize
    ) {
        Page<BuyerResponse> buyerModels = buyerService.findAllUsers(page , pageSize);
        model.addAttribute("danhSachBuyer", buyerModels);
        model.addAttribute("currentPage", page);
        return "buyer/buyer-list";
    }

    @GetMapping("/{id}/delete")
    public String deleteBuyer(@PathVariable("id") Long id) {
        buyerService.deleteByIdBuyer(id);
        return "redirect:/accounts";
    }

    @GetMapping("/{id}/edit")
    public String forwardToUpdateForm(@PathVariable("id") Long id, Model model) {
        UpdateBuyerRequest updateBuyer = buyerService.findBuyerById(id);
        model.addAttribute("buyerToiMuonCapNhat", updateBuyer);
        return "buyer/update-buyer";
    }

    @PostMapping("/update")
    public String updateBuyer(@ModelAttribute("userToiMuonCapNhat") @Valid UpdateBuyerRequest updateBuyerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "buyer/update-buyer";
        }
        buyerService.updateBuyer(updateBuyerRequest);
        return "redirect:/accounts";
    }

    @GetMapping("/{id}/activations")
    public String changeBuyerActivation(@PathVariable("id") Long id, Model model) {
        buyerService.changeBuyerActivation(id);
        return "redirect:/accounts";
    }


}
