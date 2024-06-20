package org.example.finalgradservice1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DepartmentDto {

    private Integer departmentId;

    @NotNull(message = "Department name cannot be null")
    @NotBlank(message = "Department name cannot be blank")
    private String departmentName;

    private Set<Integer> employeeIds = new HashSet<>();

    // Constructors
    public DepartmentDto() {
    }

    public DepartmentDto(Integer departmentId, String departmentName, Set<Integer> employeeIds) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        if (employeeIds != null) {
            this.employeeIds.addAll(employeeIds);
        }
    }

    public void addEmployeeId(Integer employeeId) {
        this.employeeIds.add(employeeId);
    }


}