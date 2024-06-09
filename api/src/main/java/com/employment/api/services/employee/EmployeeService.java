package com.employment.api.services.employee;


import com.employment.api.dto.employee.EmployeeDto;
import com.employment.api.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    Employee createEmployee(EmployeeDto employeeDto);

    Employee updateEmployee(EmployeeDto employeeDto, int id);

    Employee getEmployee(int id);

    List<Employee> getAllEmployee();

    Employee deleteEmployee(int id);
}
