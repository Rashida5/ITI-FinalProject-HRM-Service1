package org.example.finalgradservice1.repository;

import org.example.finalgradservice1.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
