package com.employment.api.controller;


import com.employment.api.dto.ApiResponse;
import com.employment.api.dto.employee.EmployeeDto;
import com.employment.api.entities.Employee;
import com.employment.api.services.employee.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<Employee> registerEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        Employee employeeCreated = employeeService.createEmployee(employeeDto);
        if(employeeCreated == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeCreated);
    }

    @GetMapping
    public ApiResponse<List<Employee>> getAllEmployee(){
        List<Employee> employees = employeeService.getAllEmployee();

        return new ApiResponse<>(HttpStatus.OK.value(), "All employee found", employees);
    }

    @GetMapping("/{id}")
    public ApiResponse<Employee> getEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);

        return new ApiResponse<>(HttpStatus.OK.value(), "Employee found successfully", employee);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Employee> deleteEmployee(@PathVariable int id){
        Employee employee = employeeService.deleteEmployee(id);

        return new ApiResponse<>(HttpStatus.OK.value(), "Employee deleted successfully");
    }


}
