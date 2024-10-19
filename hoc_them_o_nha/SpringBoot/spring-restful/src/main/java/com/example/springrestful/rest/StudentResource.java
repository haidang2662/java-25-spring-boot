package com.example.springrestful.rest;

import com.example.springrestful.model.request.StudentCreationRequest;
import com.example.springrestful.model.response.StudentResponse;
import com.example.springrestful.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // = @Controller + @ResponseBody (luôn trả ra ResponseEntity dưới dạng JSON)
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentResource {

    StudentService studentService;

    @GetMapping
    public List<StudentResponse> getListStudent() {
        return studentService.getListStudent();
    }

//    @GetMapping
//    public ResponseEntity<List<StudentResponse>> getListStudent() {
//        List<StudentResponse> data = studentService.getListStudent();
//        return ResponseEntity.ok(data);
//    }

    @GetMapping("/{id}")
    public StudentResponse getStudentDetails(@PathVariable("id") Long idStudent) throws ClassNotFoundException {
        return studentService.getStudentDetails(idStudent);
    }

    @PostMapping
    public StudentResponse createStudent(@RequestBody StudentCreationRequest request) {
        return studentService.createStudent(request);
    }

//    @PostMapping
//    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentCreationRequest request) {
//        StudentResponse data = studentService.createStudent(request);
//        return ResponseEntity.created(null).body(data);
//    }

    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @RequestBody StudentCreationRequest request) throws ClassNotFoundException {
        return studentService.updateStudent(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }


}
