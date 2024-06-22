package org.example.finalgradservice1.service;

import org.example.finalgradservice1.dto.*;
import org.example.finalgradservice1.mapper.EmployeeMapper;
import org.example.finalgradservice1.mapper.JobMapper;
import org.example.finalgradservice1.model.*;
import org.example.finalgradservice1.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    public boolean saveJob(JobDto jobDto) {
        Job job = jobMapper.toEntity(jobDto);
        Job savedJob = jobRepository.save(job);
        return savedJob != null && savedJob.getJobId() > 0;
    }

    public JobDto getJobById(Integer id) {
        Job job = jobRepository.findActiveById(id);
        return jobMapper.toDTO(job);
    }

    public Set<JobDto> getAllJobs() {
        Set<Job> jobs = jobRepository.findAllActive();
        return jobMapper.toDTOSet(jobs);
    }

    public boolean updateJob(JobDto jobDto) {
        Job job = jobRepository.findActiveById(jobDto.getJobId());
        if (job != null) {
            job = jobMapper.updateEntityFromDto(jobDto, job);
            jobRepository.save(job);
            return true;
        }
        return false;
    }

    public boolean deleteJob(Integer id) {
        Job job = jobRepository.findActiveById(id);
        if (job != null) {
            job.setExisted(false);
            jobRepository.save(job);
            return true;
        }
        return false;
    }

    public List<JobDto> getJobEmployeeCounts() {
        List<Object[]> jobEmployeeCounts = jobRepository.findJobEmployeeCounts();
        return jobEmployeeCounts.stream()
                .map(jobMapper::mapToJobDtoWithEmployeeCount)
                .collect(Collectors.toList());
    }

//    public List<EmployeeDto> getEmployeesByJobName(String jobName) {
//        List<Object[]> employeesByJobName = jobRepository.findEmployeesByJobName(jobName);
//        return employeesByJobName.stream()
//                .map(jobMapper::mapToEmployeeDto)
//                .collect(Collectors.toList());
//    }
}