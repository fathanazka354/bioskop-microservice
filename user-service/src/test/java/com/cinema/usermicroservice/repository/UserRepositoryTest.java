package com.cinema.usermicroservice.repository;

import com.cinema.usermicroservice.UserApplication;
import com.cinema.usermicroservice.model.Customer;
import com.cinema.usermicroservice.model.Employee;
import com.cinema.usermicroservice.service.CustomerServiceImpl;
import com.cinema.usermicroservice.service.EmployeeServiceImpl;
import com.cinema.usermicroservice.utils.MockDataUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
class UserRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private CustomerServiceImpl service;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private CustomerServiceImpl customerService;

    @Mock
    private EmployeeServiceImpl employeeServiceImpl;
    @Mock
    BCryptPasswordEncoder passwordEncoder;

    Customer customer = new Customer();
    Employee employee = new Employee();
    List<Customer> customerList = new ArrayList<>();
    List<Employee> employeeList = new ArrayList<>();

    @BeforeEach
    void setUp() throws ParseException {
        MockDataUser mockDataUser = new MockDataUser();
        mockDataUser.customerDataFaker(customer, passwordEncoder);
        mockDataUser.employeeDataFaker(employee, passwordEncoder);

        customerList.add(customer);
        employeeList.add(employee);
    }

    @Test
    @DisplayName("When All Data Customer is Successfull")
    void whenAllData_thenReturnCustomer() {
        when(service.getAllCustomer()).thenReturn(customerList);
        System.out.println("service = "+service.getAllCustomer());
        System.out.println("customerList = "+customerList);
        assertEquals(customerList, service.getAllCustomer());
    }

    @Test
    @DisplayName("When All Data Customer is Not Successfully")
    void whenAllData_thenReturnFalseCustomer(){
        List<Customer> customerList1 = service.getAllCustomer();
        Throwable throwable = assertThrows(IndexOutOfBoundsException.class, () -> customerList1.get(1));
        assertNotEquals("Index: 0, Size: 0", throwable.getMessage());
    }

//    employee GET

    @Test
    @DisplayName("When All Data Employee is Successfull")
    void whenAllData_thenReturnEmployee() {
        when(employeeServiceImpl.getAllEmployee()).thenReturn(employeeList);
        System.out.println("service = "+employeeServiceImpl.getAllEmployee());
        System.out.println("customerList = "+employeeList);
        assertEquals(employeeList, employeeServiceImpl.getAllEmployee());
    }

    @Test
    @DisplayName("When All Data Employee is Not Successfully")
    void whenAllData_thenReturnFalseEmployee(){
        List<Employee> employeeList1 = employeeServiceImpl.getAllEmployee();
        Throwable throwable = assertThrows(IndexOutOfBoundsException.class, () -> employeeList1.get(1));
        assertNotEquals("Index: 0, Size: 0", throwable.getMessage());
    }

    @Test
    @DisplayName("When Get Data Customer By id is Successfully")
    void whenGetDataById_thenReturnTrueCustomer(){
        when(service.saveCustomer(customer)).thenReturn(customer);
        Customer customer1 = service.saveCustomer(customer);
        System.out.println("customer1: " + customer1.getFirstName());
        System.out.println("customerOther: " + customer.getFirstName());
        assertEquals(customer1.getFirstName(), customer.getFirstName());
    }

    @Test
    @DisplayName("When customer Data successfully created")
    void whenCustomerData_successfully_created(){
        Customer customer1 = new Customer();
        Customer data = customerList.get(0);
        MockDataUser mockDataUser = new MockDataUser();
        mockDataUser.customerDataMock(customer1, data);
        when(service.saveCustomer(customer1)).thenReturn(data);
        assertEquals(service.saveCustomer(customer1).getCustomerId(), customer.getCustomerId());
    }

    @Test
    @DisplayName("When customer Data fail created")
    void whenCustomerData_fail_created(){
        Customer customer1 = new Customer();
        Customer data = customerList.get(0);
        MockDataUser mockDataUser = new MockDataUser();
        mockDataUser.customerDataMock(customer1, data);
        when(service.saveCustomer(customer1)).thenReturn(customer1);
        assertNotEquals(service.saveCustomer(customer1), data);
    }

//    employee

    @Test
    @DisplayName("When employee Data successfully created")
    void whenEmployeeData_successfully_created(){
        Employee employee1 = new Employee();
        Employee data = employeeList.get(0);
        MockDataUser mockDataUser = new MockDataUser();
        mockDataUser.employeeDataMock(employee1, data);
        when(employeeServiceImpl.saveEmployee(employee1)).thenReturn(data);
        assertEquals(employeeServiceImpl.saveEmployee(employee1).getEmployeeId(), employee.getEmployeeId());
    }

    @Test
    @DisplayName("When Employee Data fail created")
    void whenEmployeeData_fail_created(){
        Employee employee1 = new Employee();
        Employee data = employeeList.get(0);
        MockDataUser mockDataUser = new MockDataUser();
        mockDataUser.employeeDataMock(employee1, data);
        when(employeeServiceImpl.saveEmployee(employee1)).thenReturn(employee1);
        assertNotEquals(employeeServiceImpl.saveEmployee(employee1), data);
    }

    @Test
    @DisplayName("When Delete data is success")
    void whenDeleteData_successfully(){
        when(customerService.deleteCustomer(customer.getCustomerId())).thenReturn(true);
        assertTrue(customerService.deleteCustomer(customer.getCustomerId()));
    }

    @Test
    @DisplayName("When Delete data is failed")
    void whenDeleteData_failed(){
        final Long id = 0L;
        when(customerService.deleteCustomer(id)).thenReturn(false);
        assertFalse(customerService.deleteCustomer(id));
    }

//  Employee
    @Test
    @DisplayName("When Delete data Employee is success")
    void whenDeleteDataEmployee_successfully(){
        when(employeeServiceImpl.deleteEmployee(employee.getEmployeeId())).thenReturn(true);
        assertTrue(employeeServiceImpl.deleteEmployee(employee.getEmployeeId()));
    }

    @Test
    @DisplayName("When Delete data Employee is failed")
    void whenDeleteDataEmployee_failed(){
        final Long id = 0L;
        when(employeeServiceImpl.deleteEmployee(id)).thenReturn(false);
        assertFalse(employeeServiceImpl.deleteEmployee(id));
    }

}
