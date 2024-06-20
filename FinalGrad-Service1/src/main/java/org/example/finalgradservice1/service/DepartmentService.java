package org.example.finalgradservice1.service;

import org.example.finalgradservice1.dto.DepartmentDto;
import org.example.finalgradservice1.dto.EmployeeDto;
import org.example.finalgradservice1.mapper.DepartmentMapper;
import org.example.finalgradservice1.model.Department;
import org.example.finalgradservice1.repository.DepartmentRepository;
import org.example.finalgradservice1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;


    public boolean saveDepartment(DepartmentDto departmentDto){

        Department department = departmentMapper.departmentDtoToDepartment(departmentDto);

        // Log the department entity
        System.out.println("Mapped Department: " + department);

        Department savedDepartment = departmentRepository.save(department);
        if(savedDepartment != null && savedDepartment.getDepartmentId() > 0){
            System.out.println("saved successfully");
            return true;
        }
        else
            return false;
    }
}
