package com.example.university_backend.controller;

import com.example.university_backend.entity.Student;
import com.example.university_backend.entity.StudentRepository;
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
        return repository.findAll();
    }

    @GetMapping("/students/{matNr}")
    Student getStudent(@PathVariable Long matNr) {
        log.info("GET student matNr: " + matNr);
        return repository.findById(matNr).orElseThrow(() -> new IllegalArgumentException("not found - MatNr: " + matNr));
    }

    @PostMapping("/students")
    Student newStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @PutMapping("/students/{matNr}")
    Student updateStudent(@RequestBody Student updateStudent, @PathVariable Long matNr) {
        return repository.findById(matNr).
                map(student -> {
                    student.setFirstName(updateStudent.getFirstName());
                    student.setLastName(updateStudent.getLastName());
                    return repository.save(student);
                })
                .orElseThrow(() -> new IllegalArgumentException("not found - MatNr: " + matNr));
    }

    @DeleteMapping("/students/{matNr}")
    void deleteStudent(@PathVariable Long matNr) {
        log.info("DELETE student matNr: " + matNr);
        repository.deleteById(matNr);
    }
}
