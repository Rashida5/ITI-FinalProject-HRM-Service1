package org.example.finalgradservice1.controller;

import org.example.finalgradservice1.dto.*;
import org.example.finalgradservice1.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody JobDto jobDto) {
        boolean isSaved = jobService.saveJob(jobDto);
        if (isSaved) {
            return new ResponseEntity<>("Job created successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create job.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Integer id) {
        JobDto jobDto = jobService.getJobById(id);
        if (jobDto != null) {
            return new ResponseEntity<>(jobDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Set<JobDto>> getAllJobs() {
        Set<JobDto> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Integer id, @RequestBody JobDto jobDto) {
        jobDto.setJobId(id);
        boolean isUpdated = jobService.updateJob(jobDto);
        if (isUpdated) {
            return new ResponseEntity<>("Job updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update job.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Integer id) {
        boolean isDeleted = jobService.deleteJob(id);
        if (isDeleted) {
            return new ResponseEntity<>("Job deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete job.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee-counts")
    public ResponseEntity<List<JobDto>> getJobEmployeeCounts() {
        List<JobDto> jobEmployeeCounts = jobService.getJobEmployeeCounts();
        return new ResponseEntity<>(jobEmployeeCounts, HttpStatus.OK);
    }

//    @GetMapping("/employees-by-job")
//    public ResponseEntity<List<EmployeeDto>> getEmployeesByJobName(@RequestParam String jobName) {
//        List<EmployeeDto> employeesByJobName = jobService.getEmployeesByJobName(jobName);
//        return new ResponseEntity<>(employeesByJobName, HttpStatus.OK);
//    }
}