package org.example.finalgradservice1.controller;

import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;
import org.example.finalgradservice1.dto.EmployeeDto;
import org.example.finalgradservice1.enums.EmploymentStatus;
import org.example.finalgradservice1.mapper.EmployeeMapper;
import org.example.finalgradservice1.model.Employee;
import org.example.finalgradservice1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping
    public ResponseEntity<String>saveEmployee(@Valid @RequestBody EmployeeDto employeeDto){
       boolean saved= employeeService.saveEmployee(employeeDto);
       if(saved){
           return ResponseEntity.ok("Employee saved successfully");
       }
        return ResponseEntity.ok("Failed to save employee");
    }

    @GetMapping("/{employeeId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Integer employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployee(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Page<EmployeeDto>> getEmployees(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10") int size){
        Page<EmployeeDto> employees = employeeService.getEmployees(page, size);
//        for(EmployeeDto employeeDto:employees.getContent()){
//            System.out.println(employeeDto.getEmployeeId());
//        }
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<String>updateEmployee(@PathVariable Integer employeeId,@Valid @RequestBody EmployeeDto employeeDto){
        boolean updated = employeeService.updateEmployee(employeeDto, employeeId);
        if(updated){
            return ResponseEntity.ok("Employee updated successfully");
        }else{
            return ResponseEntity.ok("Failed to updated employee");
        }
    }
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeId){
        boolean deleted = employeeService.deleteEmployee(employeeId);
        if(deleted){
            return ResponseEntity.ok("deleted successfully");
        }else{
            return ResponseEntity.ok("Failed to deleted");
        }
    }
    @GetMapping("/manger/{employeeId}")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeOfManager(@PathVariable Integer employeeId){
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployeeManager(employeeId);
        return ResponseEntity.ok(employeeDtoList);
    }
    @GetMapping("/status/{statusId}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByStatus(@PathVariable Integer statusId){
        try {
            EmploymentStatus employmentStatus = EmploymentStatus.fromId(statusId);
            List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees(employmentStatus);
            return ResponseEntity.ok(employeeDtoList);
        }catch (IllegalArgumentException exception){
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

}
