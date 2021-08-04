package com.example.phuotstore.security.service;

import com.example.phuotstore.model.Customer;
import com.example.phuotstore.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomerDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private int id;

    private String customerName;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomerDetailsImpl(int id, String customerName, String email, String password,
                               Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static CustomerDetailsImpl build(Customer customer) {
        List<GrantedAuthority> authorities = customer.getcRoles().stream()
            .map(cRole -> new SimpleGrantedAuthority(cRole.getcRoleName().toString()))
            .collect(Collectors.toList());

        return new CustomerDetailsImpl(
            customer.getCustomerID(),
            customer.getCustomerName(),
            customer.getEmail(),
            customer.getPassword(),
            authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return customerName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CustomerDetailsImpl customer = (CustomerDetailsImpl) o;
        return Objects.equals(id, customer.id);
    }
}
