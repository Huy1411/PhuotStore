package com.example.phuotstore.repository;

import com.example.phuotstore.model.Customer;
import com.example.phuotstore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //API
    @Query("SELECT u FROM Customer u WHERE u.customerName = ?1")
    Optional<Customer> findByCustomerName(String customerName);

    Boolean existsByCustomerName(String customerName);

    Boolean existsByEmail(String email);

    @Query("SELECT u FROM Customer u WHERE u.customerID = ?1")
    Optional<Customer> findCustomerByID(int customerID);

    @Query("SELECT u FROM Customer u")
    Page<Customer> getAllCustomers(Pageable pageable);


    //MVC
    @Query("SELECT u FROM Customer u WHERE u.customerID = ?1")
    Customer getCustomerByID(int customerID);

    @Query("SELECT u FROM Customer u")
    List<Customer> getAllCustomers();

    @Query("SELECT u FROM Customer u")
    Page<Customer> findPaginateCustomer(Pageable pageable);

    @Query("SELECT u FROM Customer u WHERE u.customerName = ?1")
    Customer findByCustomername(String customerName);

}
