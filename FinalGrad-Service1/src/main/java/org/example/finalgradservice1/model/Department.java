package org.example.finalgradservice1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "department_name", nullable = false, length = 50)
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = true;

    // No-argument constructor
    public Department() {}

    // All-argument constructor
    public Department(Integer departmentId, String departmentName, Boolean deleted) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.deleted = deleted;
    }

    public void addEmployee (Employee employee){
        employees.add(employee);
        employee.setDepartment(this);
    }
}
