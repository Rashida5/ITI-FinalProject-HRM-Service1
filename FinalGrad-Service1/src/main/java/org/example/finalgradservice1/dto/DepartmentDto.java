package org.example.finalgradservice1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.HashSet;
import java.util.Set;

@Data
public class DepartmentDto {

    private Integer departmentId;

    @NotNull(message = "Department name cannot be null")
    @NotBlank(message = "Department name cannot be blank")
    @Size(min = 2 , max = 50, message = "Department name cannot exceed 50 or less than 2 characters")
    private String departmentName;

    @NotNull(message = "Employee IDs cannot be null")
    @UniqueElements(message = "Employee IDs must be unique")
    private Set<Integer> employeeIds = new HashSet<>();

    @NotNull(message = "Department name cannot be null")
    private Boolean deleted = true;

    // Constructors
    public DepartmentDto() {
    }

    public DepartmentDto(Integer departmentId, String departmentName, Set<Integer> employeeIds , Boolean deleted) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        if (employeeIds != null) {
            this.employeeIds.addAll(employeeIds);
        }
        this.deleted = deleted;
    }

    public void addEmployeeId(Integer employeeId) {
        this.employeeIds.add(employeeId);
    }
}