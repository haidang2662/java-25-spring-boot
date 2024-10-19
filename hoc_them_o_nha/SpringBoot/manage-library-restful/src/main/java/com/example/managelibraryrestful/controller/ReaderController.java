package com.example.managelibraryrestful.controller;

import com.example.managelibraryrestful.entity.Reader;
import com.example.managelibraryrestful.model.request.ReaderRequest;
import com.example.managelibraryrestful.model.response.ReaderResponse;
import com.example.managelibraryrestful.service.ReaderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/readers")
public class ReaderController {

    ReaderService readerService;

    @GetMapping("creation-form")
    public String showCreationForm(Model model){
        model.addAttribute("newReader" , new ReaderRequest());
        return "/reader/creation-form";
    }

    @GetMapping
    public String listReader (Model model)  {
        List<ReaderResponse> responses = readerService.getReaders();
        model.addAttribute("readers" , responses);
        return "reader/readers";
    }
}
