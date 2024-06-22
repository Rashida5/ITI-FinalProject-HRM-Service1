package org.example.finalgradservice1.mapper;

import org.example.finalgradservice1.dto.*;
import org.example.finalgradservice1.model.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JobMapper {

    public JobDto toDTO(Job job) {
        JobDto dto = new JobDto();
        dto.setJobId(job.getJobId());
        dto.setJobTitle(job.getJobTitle());
        dto.setMinSalary(job.getMinSalary());
        dto.setMaxSalary(job.getMaxSalary());
        return dto;
    }

    public Job toEntity(JobDto dto) {
        Job job = new Job();
        job.setJobId(dto.getJobId());
        job.setJobTitle(dto.getJobTitle());
        job.setMinSalary(dto.getMinSalary());
        job.setMaxSalary(dto.getMaxSalary());
        return job;
    }

    public Set<JobDto> toDTOSet(Set<Job> jobs) {
        return jobs.stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public Set<Job> toEntitySet(Set<JobDto> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    public Job updateEntityFromDto(JobDto dto, Job job) {
        job.setJobId(dto.getJobId());
        job.setJobTitle(dto.getJobTitle());
        job.setMinSalary(dto.getMinSalary());
        job.setMaxSalary(dto.getMaxSalary());
        return job;
    }

    public JobDto mapToJobDtoWithEmployeeCount(Object[] result) {
        Integer jobId = (Integer) result[0];
        Long numberOfEmployees = (Long) result[1];

        JobDto jobDto = new JobDto();
        jobDto.setJobId(jobId);
        jobDto.setNumberOfEmployees(numberOfEmployees);

        return jobDto;
    }

    public EmployeeDto mapToEmployeeDto(Object[] result) {
        Integer employeeId = (Integer) result[0];
        String firstName = (String) result[1];
        String lastName = (String) result[2];
        String departmentName = (String) result[3];


        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employeeId);
        employeeDto.setFirstName(firstName);
        employeeDto.setLastName(lastName);

        return employeeDto;
    }
}