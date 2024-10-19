package com.example.springrestful.service;

import com.example.springrestful.entity.Student;
import com.example.springrestful.model.request.StudentCreationRequest;
import com.example.springrestful.model.response.StudentResponse;
import com.example.springrestful.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    //    @Autowired
    StudentRepository studentRepository;

    ObjectMapper objectMapper;

    public List<StudentResponse> getListStudent() {
        List<Student> students = studentRepository.findAll();
        return students
                .stream()
                .map(s -> objectMapper.convertValue(s, StudentResponse.class))
                .toList();
    }

    public StudentResponse getStudentDetails(Long idStudent) throws ClassNotFoundException {
        Optional<Student> studentOptional = studentRepository.findById(idStudent);
        if (studentOptional.isEmpty()) {
            throw new ClassNotFoundException("Không tìm thấy sinh viên có id là " + idStudent);
        }
        Student student = studentOptional.get();
        return objectMapper.convertValue(student, StudentResponse.class);

//        return studentRepository.findById(idStudent)
//                .ifPresentOrElse(
//                        student -> objectMapper.convertValue(student, StudentResponse.class),
//                        () -> {
//                            throw new ClassNotFoundException("Không tìm thấy sinh viên có id là " + idStudent);
//                        }
//                );

    }

    public StudentResponse createStudent(StudentCreationRequest request) {
        Student student = objectMapper.convertValue(request, Student.class);
        student = studentRepository.save(student);
        return objectMapper.convertValue(student, StudentResponse.class);
    }

    public StudentResponse updateStudent(Long id, StudentCreationRequest request) throws ClassNotFoundException {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            throw new ClassNotFoundException("Không tìm thấy sinh viên có id là " + id);
        }
        Student student = studentOptional.get();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setAddress(request.getAddress());
        student.setDob(request.getDob());
        student.setGpa(request.getGpa());
        student.setPhone(request.getPhone());
        studentRepository.save(student);
        return objectMapper.convertValue(student,StudentResponse.class);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
