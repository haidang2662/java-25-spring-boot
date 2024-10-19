package com.example.adminweb.controller;

import com.example.adminweb.model.request.TelephoneCreationRequest;
import com.example.adminweb.model.request.UpdateTelephoneRequest;
import com.example.adminweb.model.response.TelephoneResponse;
import com.example.adminweb.service.TelephoneService;
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
@RequestMapping("/telephones")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class TelephoneController {

    TelephoneService telephoneService;

    @GetMapping("/telephone-creation-form")
    public String showTelephoneCreationForm(Model model) {
        model.addAttribute("telephoneRequest", new TelephoneCreationRequest()); // Khởi tạo đối tượng User và đưa vào model
        return "telephone/telephone-creation"; // Trả về trang đăng ký
    }

    @PostMapping
    public String processTelephoneForm(@ModelAttribute @Valid TelephoneCreationRequest telephoneRequest, Errors errors, Model model) {
        // Nếu có lỗi, trả về trang đăng ký và hiển thị lỗi
        if (errors.hasErrors()) {
            model.addAttribute("telephoneRequest", telephoneRequest); // Đưa lại thông tin form vào model
            return "telephone-creation"; // Quay lại trang đăng ký
        }
        // Nếu không có lỗi, tiến hành đăng ký và chuyển hướng
        telephoneService.createTelephone(telephoneRequest);
        return "redirect:/telephones";
    }

    @GetMapping
    public String getTelephones(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "6") int pageSize
    ) {
        Page<TelephoneResponse> pageData = telephoneService.findAllTelephones(page, pageSize);
        model.addAttribute("danhSachTelephone", pageData);
        model.addAttribute("currentPage", page);
        return "telephone/telephone-list";
    }

    @GetMapping("/{id}/delete")
    public String deleteTelephone(@PathVariable("id") Long id) {
        telephoneService.deleteByIdTelephone(id);
        return "redirect:/telephones";
    }

    @GetMapping("/{id}/update-form")
    public String forwardToUpdateForm(@PathVariable("id") Long id, Model model) {
        UpdateTelephoneRequest updateTelephoneRequest = telephoneService.findTelephoneById(id);
        model.addAttribute("telephoneToiMuonCapNhat", updateTelephoneRequest);
        return "telephone/update-telephone";
    }

    @PostMapping("/update")
    public String updateTelephone(@ModelAttribute("telephoneToiMuonCapNhat") @Valid UpdateTelephoneRequest updateTelephoneRequest,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "telephone/update-telephone";
        }
        telephoneService.updateTelephone(updateTelephoneRequest);
        return "redirect:/telephones";
    }

    @GetMapping("/{id}/activations")
    public String changeTelephoneActivation(@PathVariable("id") Long id) {
        telephoneService.changeTelephoneActivation(id);
        return "redirect:/telephones";
    }
}
