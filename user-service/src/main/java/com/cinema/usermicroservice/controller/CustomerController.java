package com.cinema.usermicroservice.controller;

import com.cinema.usermicroservice.dto.ResponseData;
import com.cinema.usermicroservice.model.Customer;
import com.cinema.usermicroservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Customer>> getCustomerById(@PathVariable Long id){
        ResponseData responseData = new ResponseData();
        responseData.setStatus(true);
        responseData.setPayload(customerService.getCustomerById(id));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<Customer>>> getCustomers(){
        ResponseData responseData = new ResponseData();
        responseData.setStatus(true);
        responseData.setPayload(customerService.getAllCustomer());
        return ResponseEntity.ok(responseData);
    }

    @PostMapping
    public ResponseEntity<Customer> postCustomer(@Valid @RequestBody Customer customer){
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<Customer>> postCustomers(@Valid @RequestBody List<Customer> customer) throws InterruptedException {
        return new ResponseEntity<>(customerService.saveCustomers(customer), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
