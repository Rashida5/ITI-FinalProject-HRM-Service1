package org.example.finalgradservice1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Integer jobId;

    @Column(name = "job_title", nullable = false, length = 50)
    private String jobTitle;

    @Column(name = "min_salary", precision = 10, scale = 2)
    private Long minSalary;

    @Column(name = "max_salary", precision = 10, scale = 2)
    private Long maxSalary;

    @OneToMany(mappedBy = "job")
    private Set<Employee> employees = new HashSet<>();

    // No-argument constructor
    public Job() {

    }
    // All-argument constructor
    public Job(Integer jobId, String jobTitle, Long minSalary, Long maxSalary) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;

    }

    public void addEmployee (Employee employee){
        employees.add(employee);
        employee.setJob(this);
    }

}
