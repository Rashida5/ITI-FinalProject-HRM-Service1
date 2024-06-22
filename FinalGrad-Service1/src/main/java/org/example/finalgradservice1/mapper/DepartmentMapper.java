package org.example.finalgradservice1.mapper;

import org.example.finalgradservice1.dto.DepartmentDto;
import org.example.finalgradservice1.model.Department;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DepartmentMapper {

    public  DepartmentDto toDTO(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setDepartmentId(department.getDepartmentId());
        dto.setDepartmentName(department.getDepartmentName());
        return dto;
    }

    public  Department toEntity(DepartmentDto dto) {
        Department department = new Department();
        department.setDepartmentId(dto.getDepartmentId());
        department.setDepartmentName(dto.getDepartmentName());
        return department;
    }

    public  Set<DepartmentDto> toDTOSet(Set<Department> departments) {
        return departments.stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public  Set<Department> toEntitySet(Set<DepartmentDto> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    public  Department updateEntityFromDto(DepartmentDto dto , Department department) {
        department.setDepartmentId(dto.getDepartmentId());
        department.setDepartmentName(dto.getDepartmentName());
        return department;
    }


}
