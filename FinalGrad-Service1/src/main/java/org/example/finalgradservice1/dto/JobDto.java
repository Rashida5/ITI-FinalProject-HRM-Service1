package org.example.finalgradservice1.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.*;

@Data
public class JobDto {

    private Integer jobId;

    @NotBlank(message = "Job title cannot be blank")
    @Length(min = 1, max = 50, message = "Job title must be between 1 and 50 characters")
    private String jobTitle;

    @NotNull(message = "Minimum salary cannot be null")
    @Range(min = 0, max = 999999, message = "Minimum salary must be between 0 and 999999")
    private Long minSalary;

    @NotNull(message = "Maximum salary cannot be null")
    @Range(min = 0, max = 999999, message = "Maximum salary must be between 0 and 999999")
    private Long maxSalary;

    @NotNull(message = "Job name cannot be null")
    private Boolean existed = true;

    private Long numberOfEmployees;

    // Constructors
    public JobDto() {
    }

    public JobDto(Integer jobId, String jobTitle, Long minSalary, Long maxSalary , Boolean existed) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.existed = existed;
    }
}