package com.example.phuotstore.service.impl;


import com.example.phuotstore.model.Customer;
import com.example.phuotstore.repository.CRoleRepository;
import com.example.phuotstore.repository.CustomerRepository;
import com.example.phuotstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Customer> getAllCustomers() {
        try {
            List<Customer> customers = customerRepository.getAllCustomers();
            return customers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getCustomerByID(int customerID) {
        try {
            Customer customer = customerRepository.getCustomerByID(customerID);
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean saveCustomer(Customer customer) {
        try {
            customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
            customerRepository.save(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int customerID) {
        try {
            Customer customer = customerRepository.getCustomerByID(customerID);
            customerRepository.delete(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
            customerRepository.save(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkCustomerName(String customerName) {
        Customer customer = customerRepository.findByCustomername(customerName);
        if (customer == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<Customer> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.customerRepository.findPaginateCustomer(pageable);
    }


}
