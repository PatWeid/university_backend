package com.example.university_backend.controller;

import com.example.university_backend.entity.Student;
import com.example.university_backend.entity.StudentRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    private final StudentRepository repository;


    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/students")
    List<Student> all() {
        log.info("GET all students");
        return repository.findAll();
    }

    @GetMapping("/students/{id}")
    Student getStudent(@PathVariable Long id) {
        log.info("GET student matNr: " + id);
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found - MatNr: " + id));
    }

    @PostMapping("/students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error"),
            @ApiResponse(responseCode = "200", description = "Successful retrieval") })
    Student newStudent(@RequestBody Student student) {
        log.info("POST new student: " + student);
        return repository.save(student);
    }

    @PutMapping("/students/{id}")
    Student updateStudent(@RequestBody Student updateStudent, @PathVariable Long id) {
        log.info("UPDATE student: " + updateStudent);
        return repository.findById(id).
                map(student -> {
                    student.setId(updateStudent.getId());
                    student.setFirstName(updateStudent.getFirstName());
                    student.setLastName(updateStudent.getLastName());
                    student.setDob(updateStudent.getDob());
                    student.setJoiningDate(updateStudent.getJoiningDate());
                    student.setGender(updateStudent.getGender());
                    student.setDepartment(updateStudent.getDepartment());
                    student.setEmail(updateStudent.getEmail());
                    return repository.save(student);
                })
                .orElseThrow(() -> new IllegalArgumentException("not found - MatNr: " + id));
    }

    @DeleteMapping("/students/{id}")
    void deleteStudent(@PathVariable Long id) {
        log.info("DELETE student matNr: " + id);
        repository.deleteById(id);
    }
}
