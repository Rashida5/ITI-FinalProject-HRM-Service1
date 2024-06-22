package org.example.finalgradservice1.repository;

import org.example.finalgradservice1.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    @Query("SELECT d FROM Department d WHERE d.deleted = true")
    Set<Department> findAllActive();

    @Query("SELECT d FROM Department d WHERE d.departmentId = :id AND d.deleted = true")
    Department findActiveById(Integer id);

    @Query("SELECT d FROM Department d JOIN FETCH d.employees e WHERE d.departmentId = :departmentId AND d.deleted = true")
    Department findActiveWithEmployeesById(Integer departmentId);


}
