package com.example.phuotstore.security.service;

import com.example.phuotstore.model.Customer;
import com.example.phuotstore.model.User;
import com.example.phuotstore.repository.CustomerRepository;
import com.example.phuotstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByCustomerName(customerName)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with customer name: " + customerName));

        return CustomerDetailsImpl.build(customer);
    }

}
