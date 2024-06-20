package org.example.finalgradservice1.repository;

import org.example.finalgradservice1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
