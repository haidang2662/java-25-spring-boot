package com.example.springmvctest.controller;

import com.example.springmvctest.entity.Product;
import com.example.springmvctest.model.request.SearchProductRequest;
import com.example.springmvctest.model.response.PageResponse;
import com.example.springmvctest.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

    // DTO - data transfer object
    // vm - view model

    ProductService productService;

    @GetMapping
    public String searchProduct(Model model, @ModelAttribute("searchProduct") SearchProductRequest request) {
        PageResponse<Product> products = productService.searchProduct(request);
        model.addAttribute("danhSachSanPham", products);
        return "index";
    }

}
