package com.cinema.usermicroservice.utils;

import com.cinema.usermicroservice.model.Customer;
import com.cinema.usermicroservice.model.Employee;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class MockDataUser {
    public Customer customerDataMock(Customer customer, Customer data){
        customer.setPassword(data.getPassword());
        customer.setEmail(data.getEmail());
        customer.setCustomerId(data.getCustomerId());
        customer.setCreatedAt(data.getCreatedAt());
        customer.setUpdatedAt(data.getUpdatedAt());
        customer.setPoint(data.getPoint());
        customer.setFirstName(data.getFirstName());
        customer.setLastName(data.getLastName());
        customer.setCash(data.getCash());
        customer.setBirthday(data.getBirthday());
        return customer;
    }

    public Employee employeeDataMock(Employee employee, Employee data){
        employee.setPassword(data.getPassword());
        employee.setEmail(data.getEmail());
        employee.setEmployeeId(data.getEmployeeId());
        employee.setCreatedAt(data.getCreatedAt());
        employee.setUpdatedAt(data.getUpdatedAt());
        employee.setFirstName(data.getFirstName());
        employee.setLastName(data.getLastName());
        employee.setBirthday(data.getBirthday());
        return employee;
    }

    public Customer customerDataFaker(Customer customer, BCryptPasswordEncoder passwordEncoder) throws ParseException {
        customer.setPassword(passwordEncoder.encode("abcd"));
        customer.setCustomerId(1L);
        customer.setAddress("yogya");
        customer.setBirthday(LocalDate.ofEpochDay(12- 7 -2000));
        customer.setFirstName("alex");
        customer.setLastName("Pramono");
        customer.setCash(BigInteger.valueOf(1220));
        customer.setPoint(12);
        customer.setEmail("alex@mail.com");
        customer.setLastName("Pramono");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-07-12 20:20:20");
        customer.setCreatedAt(date);

        return customer;
    }

    public Employee employeeDataFaker(Employee employee, BCryptPasswordEncoder passwordEncoder) throws ParseException {
        employee.setPassword(passwordEncoder.encode("abcd"));
        employee.setEmployeeId(1L);
        employee.setAddress("yogya");
        employee.setBirthday(LocalDate.ofEpochDay(12- 7 -2000));
        employee.setFirstName("alex");
        employee.setLastName("Pramono");
        employee.setEmail("alex@mail.com");
        employee.setLastName("Pramono");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-07-12 20:20:20");
        employee.setCreatedAt(date);

        return employee;
    }
}
