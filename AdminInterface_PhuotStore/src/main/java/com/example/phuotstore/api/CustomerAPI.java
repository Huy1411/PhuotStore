package com.example.phuotstore.api;

import com.example.phuotstore.dto.CustomerDTO;
import com.example.phuotstore.model.CRole;
import com.example.phuotstore.model.Customer;
import com.example.phuotstore.model.Role;
import com.example.phuotstore.model.User;
import com.example.phuotstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerAPI {

    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CRoleRepository cRoleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping //read data
    public ResponseEntity<Page<Customer>> getAllCustomers(Pageable pageable) {
        return ResponseEntity.ok(customerRepository.getAllCustomers(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerByID(@PathVariable int id) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByID(id);
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalCustomer.get());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable int id, @Valid @RequestBody CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByID(id);
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Customer customer = new Customer(customerDTO.getCustomerName(),
            customerDTO.getEmail(),
            encoder.encode(customerDTO.getPassword()));

        Set<String> strRoles = customerDTO.getRoles();
        Set<CRole> cRoles = new HashSet<>();
        if (strRoles == null) {
            CRole userRole = cRoleRepository.findByCRoleName(ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            cRoles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "user":
                        CRole userRole = cRoleRepository.findByCRoleName(ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        cRoles.add(userRole);
                        break;
                }
            });
        }
        customer.setcRoles(cRoles);
        customer.setCustomerID(optionalCustomer.get().getCustomerID());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(customerDTO.getAddress());
        customer.setAvatar(customerDTO.getAvatar());
        customerRepository.save(customer);
        return ResponseEntity.ok(optionalCustomer.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteCustomer(@PathVariable int id) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByID(id);
        if (!optionalCustomer.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        customerRepository.delete(optionalCustomer.get());
        return ResponseEntity.noContent().build();
    }
}
