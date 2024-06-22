package org.example.finalgradservice1.service;

import org.example.finalgradservice1.dto.DepartmentDto;
import org.example.finalgradservice1.dto.EmployeeDto;
import org.example.finalgradservice1.mapper.DepartmentMapper;
import org.example.finalgradservice1.model.Department;
import org.example.finalgradservice1.model.Employee;
import org.example.finalgradservice1.repository.DepartmentRepository;
import org.example.finalgradservice1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;


    public boolean saveDepartment(DepartmentDto departmentDto){
        Department department = departmentMapper.toEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return savedDepartment != null && savedDepartment.getDepartmentId() > 0;
    }

    public DepartmentDto getDepartmentById(Integer id) {
        Department department = departmentRepository.findActiveById(id);
        return departmentMapper.toDTO(department);
    }

    public Set<DepartmentDto> getAllDepartments() {
        Set<Department> departments = departmentRepository.findAllActive();
        return departmentMapper.toDTOSet(departments);
    }

    public boolean updateDepartment(DepartmentDto departmentDto) {
        Department department = departmentRepository.findActiveById(departmentDto.getDepartmentId());
        if (department != null) {
            department = departmentMapper.updateEntityFromDto(departmentDto, department);
            departmentRepository.save(department);
            return true;
        }
        return false;
    }

    public boolean deleteDepartment(Integer id) {
        Department department = departmentRepository.findActiveById(id);
        if (department != null) {
            department.setExisted(false);
            departmentRepository.save(department);
            return true;
        }
        return false;
    }

    public Page<Employee> getEmployeesByDepartmentId(Integer departmentId, int page, int size) {
        Department department = departmentRepository.findActiveWithEmployeesById(departmentId);
        if (department != null) {
            Pageable pageable = PageRequest.of(page, size);
            List<Employee> employees = department.getEmployees().stream().toList();
            int start = Math.min((int) pageable.getOffset(), employees.size());
            int end = Math.min((start + pageable.getPageSize()), employees.size());
            return new PageImpl<>(employees.subList(start, end), pageable, employees.size());
        }
        return Page.empty();
    }

    public List<Employee> getEmployeesByDepartmentName(String departmentName) {
        return departmentRepository.findEmployeesByDepartmentName(departmentName);
    }


}
