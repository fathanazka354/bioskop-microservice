package com.cinema.usermicroservice.service;

import com.cinema.usermicroservice.exception.DataNotFoundException;
import com.cinema.usermicroservice.model.Customer;
import com.cinema.usermicroservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
//    @Autowired
//    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return unwrapCustomer(customer, id);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
//        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }
    @Override
    public List<Customer> saveCustomers(List<Customer> customer) throws InterruptedException {
        final List customers = new ArrayList();
        Runnable runnable = () -> {
            for (int i = 0; i < customer.size() -1; i++){
                customers.set(i, customerRepository.saveAll(customer));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        return customers;
    }


    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        if (customerRepository.findById(id).isEmpty()){
            return true;
        }
        return false;
    }

    static Customer unwrapCustomer(Optional<Customer> entity, Long id){
        if (entity.isPresent()) return entity.get();
        else {
            log.error("Id "+ id +" Not present in request");
            throw new DataNotFoundException(id);
        }
    }
}
