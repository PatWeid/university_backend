package com.example.university_backend.controller;



import com.example.university_backend.entity.Staff;
import com.example.university_backend.repository.StaffRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StaffController {
    private static final Logger log = LoggerFactory.getLogger(StaffController.class);

    private final StaffRepository repository;

    public StaffController(StaffRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/staff")
    List<Staff> all() {
        log.info("GET all staff");
        return repository.findAll();
    }

    @GetMapping("staff/{id}")
    Staff getStaff(@PathVariable Long id) {
        log.info("GET staff id: " + id);
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Staff not found - id: " + id));
    }

    @PostMapping("staff")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error"),
            @ApiResponse(responseCode = "200", description = "Successful retrieval")
    })
    Staff newStaff(@RequestBody Staff staff) {
        log.info("POST new staff " + staff);
        return repository.save(staff);
    }

    @PutMapping("staff/{id}")
    Staff updateStaff(@RequestBody Staff updateStaff, @PathVariable Long id) {
        log.info("UPDATE staff: " + updateStaff);
        return repository.findById(updateStaff.getId()).
                map(staff -> {
                    staff.setStaffID(updateStaff.getStaffID());
                    staff.setFirstName(updateStaff.getFirstName());
                    staff.setLastName(updateStaff.getLastName());
                    staff.setDob(updateStaff.getDob());
                    staff.setGender(updateStaff.getGender());
                    staff.setEmail(updateStaff.getEmail());
                    return repository.save(staff);
                }).orElseThrow(() -> new IllegalArgumentException("Staff not found - id: " + id));
    }

    @DeleteMapping("staff/{id}")
    void deleteStaff(@PathVariable Long id) {
        log.info("DELETE staff - id: " + id);
        repository.deleteById(id);
    }
}
