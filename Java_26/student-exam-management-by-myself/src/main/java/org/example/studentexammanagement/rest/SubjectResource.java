package org.example.studentexammanagement.rest;

import lombok.AllArgsConstructor;
import org.example.studentexammanagement.model.request.SubjectRequest;
import org.example.studentexammanagement.model.response.SubjectResponse;
import org.example.studentexammanagement.service.SubjectService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subjects")
@AllArgsConstructor
public class SubjectResource {

    SubjectService subjectService;

    @PostMapping
    public SubjectResponse createSubject(@RequestBody SubjectRequest request){
        return subjectService.createSubject(request);
    };
}
