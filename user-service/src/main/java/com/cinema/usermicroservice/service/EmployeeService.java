package com.cinema.usermicroservice.service;

import com.cinema.usermicroservice.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(Long id);
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployee();
    boolean deleteEmployee(Long id);
}
