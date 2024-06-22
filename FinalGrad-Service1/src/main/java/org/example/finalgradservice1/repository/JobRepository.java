package org.example.finalgradservice1.repository;

import org.example.finalgradservice1.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface JobRepository extends JpaRepository<Job, Integer> {

    @Query("SELECT j FROM Job j WHERE j.existed = true")
    Set<Job> findAllActive();

    @Query("SELECT j FROM Job j WHERE j.jobId = :id AND j.existed = true")
    Job findActiveById(Integer id);

    @Query("SELECT j.jobId as jobId, count(e.employeeId) as numberOfEmployees " +
            "FROM Job j " +
            "LEFT JOIN j.employees e " +
            "WHERE j.existed = true " +
            "GROUP BY j.jobId")
    List<Object[]> findJobEmployeeCounts();

//    @Query("SELECT e.employeeId as employeeId, e.firstName as firstName, e.lastName as lastName" +
//            "FROM Employee e " +
//            "JOIN e.job j " +
//            "WHERE j.jobTitle = :jobTitle")
//    List<Object[]> findEmployeesByJobName(@Param("jobTitle") String jobTitle);
}
