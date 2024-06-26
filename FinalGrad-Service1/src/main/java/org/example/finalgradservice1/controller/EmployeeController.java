package org.example.finalgradservice1.controller;

import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;
import org.example.finalgradservice1.consumer.SalaryDto;
import org.example.finalgradservice1.dto.EmployeeDto;
import org.example.finalgradservice1.enums.EmploymentStatus;
import org.example.finalgradservice1.mapper.EmployeeMapper;
import org.example.finalgradservice1.model.Employee;
import org.example.finalgradservice1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
//@CrossOrigin(allowedHeaders ={"*"},
//        methods = {RequestMethod.POST,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PATCH,RequestMethod.PUT},
//        origins = {"*"}
//)
//@CrossOrigin(origins = "http://localhost:4200",
//        methods = {RequestMethod.POST,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PATCH,RequestMethod.PUT, RequestMethod.OPTIONS}
//)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping
    //@CrossOrigin(origins = "*" )
    public ResponseEntity<String>saveEmployee(@Valid @RequestBody EmployeeDto employeeDto){
       EmployeeDto saved= employeeService.saveEmployee(employeeDto);
       if(saved==null)
           return ResponseEntity.ok("Failed to save employee");
       else{
           SalaryDto salaryDto = new SalaryDto();
           salaryDto.setEmpId(saved.getEmployeeId());
           salaryDto.setAmount(saved.getCurrentSalary());
           RestTemplate restTemplate = new RestTemplate();
           String requestUrl
                   = "http://localhost:8222/api/v1/vacations/salary";
           HttpEntity<SalaryDto> request = new HttpEntity<>(salaryDto);
           ResponseEntity<SalaryDto> response
                   = restTemplate.postForEntity(requestUrl, request,SalaryDto.class);

           if(response.getBody()!=null && response.getBody().getAmount()!=null){
               employeeDto.setCurrentSalary(response.getBody().getAmount());
               return  ResponseEntity.ok("Employee Saved , Failed to save Salary");
           }else{
               return ResponseEntity.ok("Employee Saved and Salary saved successfully");
           }
       }
    }

    @GetMapping("/{employeeId}")
    //@CrossOrigin(origins = "*")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Integer employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployee(employeeId);

        return ResponseEntity.ok(employeeDto);
    }


    @GetMapping("/detail/{employeeId}")
    //@CrossOrigin(origins = "*")
    public ResponseEntity<EmployeeDto> getEmployeeDetail(@PathVariable Integer employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployee(employeeId);


        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8222/api/v1/vacations/salary";
        String requestUrl = fooResourceUrl + "/" + employeeId;
        ResponseEntity<SalaryDto> response
                = restTemplate.getForEntity(requestUrl, SalaryDto.class);

        if(response.getBody()!=null && response.getBody().getAmount()!=null){
            employeeDto.setCurrentSalary(response.getBody().getAmount());
        }

        return ResponseEntity.ok(employeeDto);
    }
    @GetMapping
   // @CrossOrigin(origins = "*")
    public ResponseEntity<Page<EmployeeDto>> getEmployees(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10") int size){

        Page<EmployeeDto> employees = employeeService.getEmployees(page, size);
//        for(EmployeeDto employeeDto:employees.getContent()){
//            System.out.println(employeeDto.getEmployeeId());
//        }
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{employeeId}")
    //@CrossOrigin(origins = "*")
    public ResponseEntity<String>updateEmployee(@PathVariable Integer employeeId,@Valid @RequestBody EmployeeDto employeeDto){
        boolean updated = employeeService.updateEmployee(employeeDto, employeeId);
        if(!updated) {
            return ResponseEntity.ok("Failed to updated employee");
        }
        else{
                SalaryDto salaryDto = new SalaryDto();
                salaryDto.setEmpId(employeeId);
                salaryDto.setAmount(employeeDto.getCurrentSalary());
                RestTemplate restTemplate = new RestTemplate();
                System.out.println(employeeDto.getCurrentSalary());
                String requestUrl
                        = "http://localhost:8222/api/v1/vacations/salary/"+employeeId;

                HttpEntity<SalaryDto> request = new HttpEntity<>(salaryDto);
               // HttpEntity<?> request = new HttpEntity<>(null);
            ResponseEntity<SalaryDto> response =restTemplate.postForEntity(requestUrl, request,SalaryDto.class);

                if(response.getBody()!=null && response.getBody().getAmount()!=null){
                    employeeDto.setCurrentSalary(response.getBody().getAmount());
                    return  ResponseEntity.ok("Employee updated , Salary updated successfully");
                }else{
                    return ResponseEntity.ok("Employee updated and Failed to Salary updated");
                }
            }
        }
    @DeleteMapping("/{employeeId}")
    //@CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeId){
        boolean deleted = employeeService.deleteEmployee(employeeId);
        if(deleted){
            return ResponseEntity.ok("deleted successfully");
        }else{
            return ResponseEntity.ok("Failed to deleted");
        }
    }
    @GetMapping("/manger/{employeeId}")
    //@CrossOrigin(origins = "*")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeOfManager(@PathVariable Integer employeeId){
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployeeManager(employeeId);
        return ResponseEntity.ok(employeeDtoList);
    }
    @GetMapping("/status/{statusId}")
    //@CrossOrigin(origins = "*")
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
