package org.example.finalgradservice1.mapper;

import org.example.finalgradservice1.dto.DepartmentDto;
import org.example.finalgradservice1.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDto departmentToDepartmentDto(Department department);
    Department departmentDtoToDepartment(DepartmentDto departmentDto);
}

