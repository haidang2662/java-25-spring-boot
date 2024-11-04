package com.example.manage_student_swagger.rest;

import com.example.manage_student_swagger.exceptionHandling.ObjectNotFoundException;
import com.example.manage_student_swagger.model.request.SubjectRequest;
import com.example.manage_student_swagger.model.response.SubjectResponse;
import com.example.manage_student_swagger.service.SubjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/subjects")
public class SubjectResource {

    SubjectService subjectService;

    @GetMapping
    public List<SubjectResponse> getSubjects(){
        return subjectService.getSubjects();
    }

    @GetMapping("/{id}")
    public SubjectResponse getSubjectDetails(@PathVariable("id") Long idSubject) throws ObjectNotFoundException {
        return subjectService.getSubjectDeails(idSubject);
    }

    @PostMapping
    public SubjectResponse creatSubject(@RequestBody @Valid SubjectRequest request){
        return subjectService.creatSubject(request);
    }

    @PutMapping("/{id}")
    public SubjectResponse updateSubject(@PathVariable("id") Long idSubject , @RequestBody SubjectRequest request) throws ObjectNotFoundException {
        return subjectService.updateSubject(idSubject , request);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable("id") Long idSubject){
        subjectService.deleteSubject(idSubject);
    }
}
