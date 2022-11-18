package com.cinema.usermicroservice.service;

import com.cinema.usermicroservice.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer getCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    List<Customer> saveCustomers(List<Customer> customer) throws InterruptedException;
    List<Customer> getAllCustomer();
    boolean deleteCustomer(Long id);
}
