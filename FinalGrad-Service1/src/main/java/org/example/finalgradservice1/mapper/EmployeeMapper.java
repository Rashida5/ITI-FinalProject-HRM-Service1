package org.example.finalgradservice1.mapper;

import org.example.finalgradservice1.dto.EmployeeDto;
import org.example.finalgradservice1.model.Department;
import org.example.finalgradservice1.model.Employee;
import org.example.finalgradservice1.model.Job;
import org.example.finalgradservice1.repository.DepartmentRepository;
import org.example.finalgradservice1.repository.EmployeeRepository;
import org.example.finalgradservice1.repository.JobRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {EntityMapperHelper.class})
public interface EmployeeMapper {

    @Mapping(source = "manager.employeeId", target = "managerId")
    @Mapping(source = "job.jobId", target = "jobId")
    @Mapping(source = "department.departmentId", target = "departmentId")
    EmployeeDto employeeToEmployeeDto(Employee employee);

   @Mapping(source = "managerId", target = "manager", qualifiedByName = "idToEmployee")
   @Mapping(source = "jobId", target = "job", qualifiedByName = "idToJob")
   @Mapping(source = "departmentId", target = "department", qualifiedByName = "idToDepartment")
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    @Mapping(source = "jobId", target = "job", qualifiedByName = "idToJob")
    @Mapping(source = "departmentId", target = "department", qualifiedByName = "idToDepartment")
    void updateEmployeeFromDto(EmployeeDto employeeDto, @MappingTarget Employee employee);

}
@Component
class EntityMapperHelper {

    @Autowired
    private EmployeeRepository employeeRepository;


   @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Named("idToEmployee")
    public Employee idToEmployee(Integer id) {
      return employeeRepository.findById(id).orElse(null);
    }

    @Named("idToJob")
    public Job idToJob(Integer id) {
       return jobRepository.findById(id).orElse(null);
    }

    @Named("idToDepartment")
    public Department idToDepartment(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
