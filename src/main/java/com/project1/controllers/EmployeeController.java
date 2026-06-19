package com.project1.controllers;

import com.project1.advices.ApiResponse;
import com.project1.dto.EmployeeDto;
import com.project1.dto.UpdateEmployeeDto;
import com.project1.entities.EmployeeEntity;
import com.project1.repositories.EmployeeRepository;
import com.project1.services.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private Long employeeId;

    @GetMapping(path = "/list")
    public List<EmployeeEntity> getAllEmployee (HttpServletRequest request) {

        String ipAddres = request.getRemoteAddr();
        System.out.println(ipAddres + " ip address");
        return employeeService.getAllEmployee();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<EmployeeDto> addEmployee (@RequestBody @Valid EmployeeDto employeeDto) {
       EmployeeDto employee = employeeService.addEmployee(employeeDto);
        return ResponseEntity.ok(employee);
    }

//    @PostMapping(path = "/add")
//    public ResponseEntity<ApiResponse<EmployeeDto>> addEmployee (@RequestBody @Valid EmployeeDto employeeDto) {
//        EmployeeDto employee = employeeService.addEmployee(employeeDto);
//        return  ResponseEntity.ok(new ApiResponse<>(employee));
//    }



    @PutMapping(path = "/update")
    public EmployeeDto updateEmployee (@RequestBody UpdateEmployeeDto updateEmployeeDto) {
        return employeeService.updateEmployee(updateEmployeeDto);
    }


    @DeleteMapping(path = "/delete/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee (@PathVariable Long employeeId) {
        if(employeeService.deleteEmployee(employeeId)) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
            @PathVariable Long employeeId) {


        return ResponseEntity.ok(
                employeeService.getEmployeeById(employeeId));
    }

}
