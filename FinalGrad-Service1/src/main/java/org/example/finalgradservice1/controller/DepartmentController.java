package org.example.finalgradservice1.controller;

import jakarta.validation.Valid;
import org.example.finalgradservice1.dto.DepartmentDto;
import org.example.finalgradservice1.model.Employee;
import org.example.finalgradservice1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentDto departmentDto) {
        boolean isSaved = departmentService.saveDepartment(departmentDto);
        if (isSaved) {
            return new ResponseEntity<>("Department created successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create department.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Integer id) {
        DepartmentDto departmentDto = departmentService.getDepartmentById(id);
        if (departmentDto != null) {
            return new ResponseEntity<>(departmentDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Set<DepartmentDto>> getAllDepartments() {
        Set<DepartmentDto> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable Integer id, @RequestBody DepartmentDto departmentDto) {
        departmentDto.setDepartmentId(id);
        boolean isUpdated = departmentService.updateDepartment(departmentDto);
        if (isUpdated) {
            return new ResponseEntity<>("Department updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update department.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Integer id) {
        boolean isDeleted = departmentService.deleteDepartment(id);
        if (isDeleted) {
            return new ResponseEntity<>("Department deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete department.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<Page<Employee>> getEmployeesByDepartmentId(@PathVariable Integer id,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "5") int size) {
        Page<Employee> employees = departmentService.getEmployeesByDepartmentId(id, page, size);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{departmentName}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentName(@PathVariable String departmentName) {
        List<Employee> employees = departmentService.getEmployeesByDepartmentName(departmentName);
        if (!employees.isEmpty()) {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
