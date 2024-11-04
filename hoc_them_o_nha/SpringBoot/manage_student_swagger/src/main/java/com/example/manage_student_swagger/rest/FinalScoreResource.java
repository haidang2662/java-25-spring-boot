package com.example.manage_student_swagger.rest;

import com.example.manage_student_swagger.entity.FinalScore;
import com.example.manage_student_swagger.exceptionHandling.ObjectNotFoundException;
import com.example.manage_student_swagger.exceptionHandling.UnprocessableEntityException;
import com.example.manage_student_swagger.model.request.FinalScoreRequest;
import com.example.manage_student_swagger.model.response.FinalScoreResponse;
import com.example.manage_student_swagger.model.response.SubjectResponse;
import com.example.manage_student_swagger.service.FinalScoreService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/final_scores")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FinalScoreResource {

    FinalScoreService finalScoreService;

    @GetMapping("/{id}")
    public FinalScoreResponse getFinalScoreDetails(@PathVariable("id") Long idFinalScore) throws ObjectNotFoundException {
        return finalScoreService.getFinalScoreDetails(idFinalScore);
    }

    @PostMapping
    public FinalScoreResponse createFinalScore(@RequestBody @Valid FinalScoreRequest request) throws ObjectNotFoundException, UnprocessableEntityException {
        return finalScoreService.createFinalScore(request);
    }

    @PutMapping("/{id}")
    public FinalScoreResponse updateFinalScore(@PathVariable("id") Long idFinalScore ,@RequestBody FinalScoreRequest request) throws ObjectNotFoundException {
        return finalScoreService.updateFinalScore(idFinalScore , request);
    }

}
