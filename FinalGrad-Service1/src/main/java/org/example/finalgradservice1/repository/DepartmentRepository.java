package org.example.finalgradservice1.repository;

import org.example.finalgradservice1.model.Department;
import org.example.finalgradservice1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    @Query("SELECT d FROM Department d WHERE d.existed = true")
    Set<Department> findAllActive();

    @Query("SELECT d FROM Department d WHERE d.departmentId = :id AND d.existed = true")
    Department findActiveById(Integer id);

    @Query("SELECT d FROM Department d JOIN FETCH d.employees e WHERE d.departmentId = :departmentId AND d.existed = true")
    Department findActiveWithEmployeesById(Integer departmentId);

    @Query("SELECT e FROM Department d JOIN d.employees e WHERE d.departmentName = :departmentName AND d.existed = true")
    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);
}
