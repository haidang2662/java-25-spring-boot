package com.example.manage_student_swagger.controller;

import com.example.manage_student_swagger.entity.FinalScore;
import com.example.manage_student_swagger.model.response.FinalScoreResponse;
import com.example.manage_student_swagger.service.FinalScoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/final_scores")
public class FinalScoreController {

    FinalScoreService finalScoreService;

    @GetMapping
    public String getListFinalScore(Model model) {
        List<FinalScoreResponse> finalScoreList = finalScoreService.getFinalScores();
        model.addAttribute("finalScores", finalScoreList);
        return "/finalScore/finalScores";
    }
}
