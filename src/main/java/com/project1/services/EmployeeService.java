package com.project1.services;

import com.project1.dto.EmployeeDto;
import com.project1.dto.UpdateEmployeeDto;
import com.project1.entities.EmployeeEntity;
import com.project1.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public List<EmployeeEntity> getAllEmployee () {
        return employeeRepository.findAll();
    }

    public EmployeeDto addEmployee (EmployeeDto employeeDto) {
        EmployeeEntity employee = modelMapper.map(employeeDto , EmployeeEntity.class);
        employeeRepository.save(employee);
        return modelMapper.map(employee , EmployeeDto.class);
    }

    public EmployeeDto updateEmployee (UpdateEmployeeDto updateEmployeeDto) {
        EmployeeEntity employee = employeeRepository.findById(updateEmployeeDto.getId()).orElseThrow();

        employee.setAge(updateEmployeeDto.getAge());
        employee.setName(updateEmployeeDto.getName());
        employee.setEmail(updateEmployeeDto.getEmail());
        employee.setAge(updateEmployeeDto.getAge());

        employeeRepository.save(employee);

        return modelMapper.map(employee , EmployeeDto.class);

    }


    public boolean deleteEmployee (Long employeeId) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new NoSuchElementException("Employee not found"));
        employeeRepository.delete(employee);

        return true;

    }

    public EmployeeDto getEmployeeById(Long employeeId) {
        EmployeeEntity employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new NoSuchElementException("Employee not found"));

        return modelMapper.map(employee, EmployeeDto.class);
    }

}
