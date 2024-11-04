package com.example.managelibrarynothymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/readers")
public class ReaderController {
    @GetMapping
    public String readers() {
        return "readers";
    }
}
