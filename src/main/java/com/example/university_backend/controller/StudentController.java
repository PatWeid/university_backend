package com.example.university_backend.controller;

import com.example.university_backend.entity.Student;
import com.example.university_backend.repository.StudentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/students")
    @Operation(summary = "get all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success"),
            @ApiResponse(responseCode = "418",
                    description = "Error")})
    ResponseEntity<List<Student>> getAllStudents() {
        log.info("GET all students");
        try {
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error GET all students");
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/students/{id}")
    @Operation(summary = "get student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success"),
            @ApiResponse(responseCode = "404",
                    description = "Student not found"),
            @ApiResponse(responseCode = "418",
                    description = "Error")})
    ResponseEntity<Student> getStudentsById(@PathVariable Long id) {
        try {
            Optional<Student> studentById = repository.findById(id);
            if (studentById.isPresent()) {
                log.info("GET student by id: " + id);
                return new ResponseEntity<>(studentById.get(), HttpStatus.OK);
            }
            log.info("GET student by id not found: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.info("Error GET student by id");
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PostMapping("/students")
    @Operation(summary = "adds a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success"),
            @ApiResponse(responseCode = "418",
                    description = "Error")
    })
    ResponseEntity<Student> newStudent(@RequestBody Student student) {
        try {
            log.info("POST new student: " + student);
            return new ResponseEntity<>(repository.save(student), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PutMapping("/students/{id}")
    @Operation(summary = "updates a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully updated the student"),
            @ApiResponse(responseCode = "404",
                    description = "Student to update not found"),
            @ApiResponse(responseCode = "418",
                    description = "Error")
    })
    ResponseEntity<Student> updateStudent(@RequestBody Student updateStudent, @PathVariable Long id) {
        try {
            Optional<Student> studentToUpdateOptional = repository.findById(id);
            if (studentToUpdateOptional.isPresent()) {
                log.info("UPDATE student: " + updateStudent);
                Student student = studentToUpdateOptional.get();
                student.setId(updateStudent.getId());
                student.setFirstName(updateStudent.getFirstName());
                student.setLastName(updateStudent.getLastName());
                student.setDob(updateStudent.getDob());
                student.setJoiningDate(updateStudent.getJoiningDate());
                student.setGender(updateStudent.getGender());
                student.setDepartment(updateStudent.getDepartment());
                student.setEmail(updateStudent.getEmail());
                return new ResponseEntity(repository.save(student), HttpStatus.OK);
            }
            log.info("UPDATE student not found: " + updateStudent);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }

    }

    @DeleteMapping("/students/{id}")
    @Operation(summary = "deletes a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully deleted the student"),
            @ApiResponse(responseCode = "418",
                    description = "Error")
    })
    ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        try {
            log.info("DELETE student id: " + id);
            repository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("students/course/{course}")
    @Operation(summary = "gets students by course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success"),
            @ApiResponse(responseCode = "418",
                    description = "Error")
    })
    ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable String course) {
        try {
            log.info("GET students by course: " + course);
            return new ResponseEntity<>(repository.findByCourse(course), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
