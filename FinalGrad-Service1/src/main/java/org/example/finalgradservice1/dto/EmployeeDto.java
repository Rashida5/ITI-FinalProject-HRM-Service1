package org.example.finalgradservice1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
@Data
public class EmployeeDto {



    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Size(max = 11, message = "Phone number can be up to 11 characters")
    private String phoneNumber;

    @NotBlank(message = "Employment status is mandatory")
    @Size(max = 20, message = "Employment status can be up to 20 characters")
    private String employmentStatus;

    @NotNull(message = "Start date is mandatory")
    private Date startDate;

    private Date endDate;

    @NotNull(message = "Manager ID is mandatory")
    private Integer managerId;

    @NotNull(message = "Job ID is mandatory")
    private Integer jobId;

    @NotNull(message = "Department ID is mandatory")
    private Integer departmentId;

    public EmployeeDto() {

    }

    public EmployeeDto(String firstName, String lastName, String email, String phoneNumber, String employmentStatus, Date startDate, Date endDate, int managerId, int jobId, int departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.managerId = managerId;
        this.jobId = jobId;
        this.departmentId = departmentId;
    }
}
