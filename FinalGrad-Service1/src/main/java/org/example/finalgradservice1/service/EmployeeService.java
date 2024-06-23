package org.example.finalgradservice1.service;

import org.example.finalgradservice1.dto.AddressDto;
import org.example.finalgradservice1.dto.EmployeeDto;
import org.example.finalgradservice1.enums.EmploymentStatus;
import org.example.finalgradservice1.mapper.AddressMapper;
import org.example.finalgradservice1.mapper.EmployeeMapper;
import org.example.finalgradservice1.model.Address;
import org.example.finalgradservice1.model.Employee;
import org.example.finalgradservice1.repository.AddressRepository;
import org.example.finalgradservice1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private AddressMapper addressMapper;
    public boolean saveEmployee(EmployeeDto employeeDto){

        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        System.out.println("Mapped Employee: "+employee);

        AddressDto addressDto = new AddressDto();
        addressDto.setCountry(employeeDto.getCountry());
        addressDto.setCity(employeeDto.getCity());
        addressDto.setState(employeeDto.getState());
        addressDto.setZipCode(employeeDto.getZipCode());
        addressDto.setStreet(employeeDto.getStreet());

        Address address = addressMapper.toEntity(addressDto);
       Address savedAddress= addressRepository.save(address);
        //employee.setAddress();
        if(savedAddress==null||savedAddress.getAddressId()==null)
            return false;

        employee.setAddress(savedAddress);
        Employee savedEmployee = employeeRepository.save(employee);

        if(savedEmployee!=null && savedEmployee.getEmployeeId() > 0){
            System.out.println("employee saved successfully");
            //need to add address of employee

            return true;
        }else{
            return false;
        }
    }
    public EmployeeDto getEmployee(int employeeId){
        Employee employee = employeeRepository.getReferenceById(employeeId);
        System.out.println(employee.getAddress().getAddressId());
        if(employee!=null && employee.getEmployeeId()>0){
            EmployeeDto employeeDto = employeeMapper.employeeToEmployeeDto(employee);
            return employeeDto;
        }else{
            return new EmployeeDto();
        }

    }
    public Page<EmployeeDto> getEmployees(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeRepository.findAllNonTerminated(pageable);
      //  Page<Employee> employeePage = employeeRepository.findAll(pageable);
        for(Employee employee: employeePage.getContent()){
            System.out.println(employee.getEmployeeId());
        }
        return employeePage.map(employeeMapper::employeeToEmployeeDto);
    }

    public boolean updateEmployee(EmployeeDto employeeDto, int employeeId){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            employeeDto.setEmployeeId(employeeId);
            employeeMapper.updateEmployeeFromDto(employeeDto, existingEmployee);
            employeeRepository.save(existingEmployee);

            return true;
        } else {
            return false;
        }

    }
    //this employee work as soft delete
    public boolean deleteEmployee(int employeeId){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if(optionalEmployee.isPresent()){
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setEmploymentStatus("TERMINATED");
            employeeRepository.save(existingEmployee);
            return true;
        }else{
            return false;
        }
    }
    public List<EmployeeDto> getAllEmployeeManager(int mangerId){
        List<Employee> employees = employeeRepository.findByManagerEmployeeId(mangerId);
        return employees.stream().map(employeeMapper::employeeToEmployeeDto).toList();
    }
    public List<EmployeeDto> getAllEmployees(EmploymentStatus employmentStatus){
        List<Employee> employees = employeeRepository.findByEmploymentStatus(employmentStatus.name());
        return employees.stream().map(employeeMapper::employeeToEmployeeDto).toList();
    }

}
