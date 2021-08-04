package com.example.phuotstore.api;

import com.example.phuotstore.model.CRole;
import com.example.phuotstore.model.Customer;
import com.example.phuotstore.model.Role;
import com.example.phuotstore.payload.request.LoginRequest;
import com.example.phuotstore.payload.request.SignupRequest;
import com.example.phuotstore.payload.response.JwtResponse;
import com.example.phuotstore.payload.response.MessageResponse;
import com.example.phuotstore.repository.CRoleRepository;
import com.example.phuotstore.repository.CustomerRepository;
import com.example.phuotstore.repository.RoleRepository;
import com.example.phuotstore.security.jwt.JwtUtils;
import com.example.phuotstore.security.service.CustomerDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/auth")
public class AuthAPI {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_MANAGER = "ROLE_MANAGER";
    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    @Qualifier("authenticationManagerCustomer")
    private AuthenticationManager authenticationManagerCustomer;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CRoleRepository cRoleRepository;

    @Autowired
    @Qualifier("encoderCustomer")
    PasswordEncoder encoderCustomer;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateCustomer(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManagerCustomer.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getCustomerName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomerDetailsImpl customerDetails = (CustomerDetailsImpl) authentication.getPrincipal();
        List<String> roles = customerDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
            customerDetails.getId(),
            customerDetails.getUsername(),
            customerDetails.getEmail(),
            roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody SignupRequest signUpRequest) {
        if (customerRepository.existsByCustomerName(signUpRequest.getCustomerName())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Customer Name is already taken!"));
        }

        if (customerRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Customer customer = new Customer(signUpRequest.getCustomerName(),
            signUpRequest.getEmail(),
            encoderCustomer.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
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
        customerRepository.save(customer);
        return ResponseEntity.ok(new MessageResponse("Registered successfully!"));
    }
}

