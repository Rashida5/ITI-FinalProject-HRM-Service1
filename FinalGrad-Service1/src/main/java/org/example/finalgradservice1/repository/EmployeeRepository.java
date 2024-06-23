package org.example.finalgradservice1.repository;

import org.example.finalgradservice1.enums.EmploymentStatus;
import org.example.finalgradservice1.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByManagerEmployeeId(Integer managerId);
    List<Employee> findByEmploymentStatus(String status);

    @Query("SELECT e FROM Employee e WHERE e.employmentStatus <> 'TERMINATED'")
    Page<Employee> findAllNonTerminated(Pageable pageable);

}
