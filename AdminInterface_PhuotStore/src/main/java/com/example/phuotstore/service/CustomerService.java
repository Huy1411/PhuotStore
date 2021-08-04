package com.example.phuotstore.service;


import com.example.phuotstore.model.Customer;
import com.example.phuotstore.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomerByID(int customerID);
    boolean saveCustomer(Customer customer);
    boolean deleteCustomer(int customerID);
    boolean updateCustomer(Customer customer);
    boolean checkCustomerName(String customerName);
    Page<Customer> findPaginated(int pageNo, int pageSize);
}
