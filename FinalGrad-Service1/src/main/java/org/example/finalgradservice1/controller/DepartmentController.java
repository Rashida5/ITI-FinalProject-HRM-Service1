package org.example.finalgradservice1.controller;

import jakarta.validation.Valid;
import org.example.finalgradservice1.dto.DepartmentDto;
import org.example.finalgradservice1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/save")
    public ResponseEntity<String> saveDepartmentAPI(@Valid @RequestBody DepartmentDto departmentDto) {
        if (departmentDto.getDepartmentName() == null || departmentDto.getDepartmentName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Department name cannot be null or empty");
        }

        // Log the departmentDto
        System.out.println("Received DepartmentDto: " + departmentDto);

        boolean saved = departmentService.saveDepartment(departmentDto);


        if (saved) {
            return ResponseEntity.ok("Department saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save department");
        }
    }
}