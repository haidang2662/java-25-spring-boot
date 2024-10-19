package com.example.adminweb.controller;

import com.example.adminweb.model.request.TelephoneBuyCreationRequest;
import com.example.adminweb.model.request.UpdateTelephoneBuyRequest;
import com.example.adminweb.model.response.TelephoneBuyerResponse;
import com.example.adminweb.model.response.TelephoneResponse;
import com.example.adminweb.model.response.BuyerResponse;
import com.example.adminweb.service.TelePhoneBuyService;
import com.example.adminweb.service.TelephoneService;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/buys")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TelephoneBuyController {

    TelephoneService telephoneService;

    BuyerService buyerService;

    TelePhoneBuyService telePhoneBuyService;

    @GetMapping("/creation-form")
    public String showTelephoneBuyCreationForm(Model model) {
        model.addAttribute("telephoneBuyRequest", new TelephoneBuyCreationRequest()); // Khởi tạo đối tượng borrow và đưa vào model

        List<TelephoneResponse> activeTelephones = telephoneService.findActiveTelephone();
        model.addAttribute("telephones", activeTelephones);

        List<BuyerResponse> activeBuyers = buyerService.findActiveBuyer();
        model.addAttribute("buyers", activeBuyers);

        return "buy/buy-creation";
    }

    @PostMapping
    public String telephoneBuy(@ModelAttribute("telephoneBuyRequest") @Valid TelephoneBuyCreationRequest telephoneBuyRequest,
                               Errors errors, Model model) {
        // Nếu có lỗi, trả về trang đăng ký và hiển thị lỗi
        if (errors.hasErrors()) {
            model.addAttribute("telephoneBuyRequest", telephoneBuyRequest); // Đưa lại thông tin form vào model
            return "buy/buy-creation"; // Quay lại trang đăng ký
        }
        // Nếu không có lỗi, tiến hành đăng ký và chuyển hướng
        telePhoneBuyService.create(telephoneBuyRequest);
        return "redirect:/buys";
    }

    @GetMapping
    public String getTelephoneBuy(
            Model model,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "6") int pageSize
    ) {
        Page<TelephoneBuyerResponse> telephoneBuyResponses = telePhoneBuyService.findAllTelephoneBuys(page , pageSize);
        model.addAttribute("danhSachTelephoneBuy", telephoneBuyResponses);
        model.addAttribute("currentPage", page);
        return "buy/buys";
    }

    @GetMapping("/{id}/delete")
    public String deleteTelephoneBuy(@PathVariable("id") Long id) {
        telePhoneBuyService.deleteByIdTelephoneBuy(id);
        return "redirect:/buys";
    }

    @GetMapping("/{id}/edit")
    public String forwardToUpdateForm(@PathVariable("id") Long id, Model model) {
        UpdateTelephoneBuyRequest updateTelephoneBuy = telePhoneBuyService.findTelephoneBuyById(id);
        model.addAttribute("telephoneBuyToiMuonCapNhat", updateTelephoneBuy);

        List<TelephoneResponse> activeTelephones = telephoneService.findActiveTelephone();
        model.addAttribute("telephones", activeTelephones);

        List<BuyerResponse> activeBuyers = buyerService.findActiveBuyer();
        model.addAttribute("buyers", activeBuyers);

        return "buy/update-buy";
    }

    @PostMapping("/update")
    public String updateTelephoneBuy(@ModelAttribute("telephoneBuyToiMuonCapNhat") @Valid UpdateTelephoneBuyRequest updateTelephoneBuyRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "buy/update-buy";
        }


//        // Tìm kiếm đối tượng User và Book dựa trên ID
//        User borrower = updateBookBorrowRequest.getBorrower(); // Giả sử bạn đã có phương thức tìm kiếm User
//        Book book = updateBookBorrowRequest.getBook();
//
//        // Kiểm tra nếu borrower là null, throw exception nếu cần
//        if (borrower == null) {
//            throw new ObjectNotFoundException("Không tìm thấy bạn đọc");
//        }
//
//        if (book == null) {
//            throw new ObjectNotFoundException("Không tìm thấy sách");
//        }
//
//

        telePhoneBuyService.updateTelephoneBuy(updateTelephoneBuyRequest);
        return "redirect:/buys";
    }
}
