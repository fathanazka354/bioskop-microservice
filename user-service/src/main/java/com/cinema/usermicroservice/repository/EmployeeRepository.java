package com.cinema.usermicroservice.repository;

import com.cinema.usermicroservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
