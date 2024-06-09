package com.employment.api.services.employee;

import com.employment.api.dto.employee.EmployeeDto;
import com.employment.api.entities.Employee;
import com.employment.api.exception.EmployeeAlreadyExist;
import com.employment.api.exception.EmployeeException;
import com.employment.api.repositories.EmployeeRepo;
import com.employment.api.utilities.PasswordUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeImpl implements EmployeeService{
    private final EmployeeRepo employeeRepo;
    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employee = employeeRepo.findByEmail(employeeDto.getEmail());
        if (employee.isPresent()) {
            throw new EmployeeAlreadyExist("Employee already exist");
        }

        Employee employeeInfo = Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .phoneNumber(employeeDto.getPhoneNumber())
                .password(PasswordUtil.hashPassword(employeeDto.getPassword()))
                .build();

        return employeeRepo.save(employeeInfo);
    }

    @Override
    public Employee updateEmployee(EmployeeDto employeeDto, int id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new EmployeeException("Employee not found"));

        Employee employeeInfo = Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .phoneNumber(employeeDto.getPhoneNumber())
                .password(PasswordUtil.hashPassword(employeeDto.getPassword()))
                .build();
        return employeeRepo.save(employeeInfo);
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeRepo.findById(id).orElseThrow(() -> new EmployeeException("Employee already exist"));
    }

    @Override
    public List<Employee> getAllEmployee() {
        return List.of((Employee) employeeRepo.findAll());
    }

    @Override
    public Employee deleteEmployee(int id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new EmployeeException("User not found"));
        employeeRepo.delete(employee);
        return employee;
    }
}
