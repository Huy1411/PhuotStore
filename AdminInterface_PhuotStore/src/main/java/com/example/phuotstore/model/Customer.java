package com.example.phuotstore.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"customer\"",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "customerName"),
        @UniqueConstraint(columnNames = "email")
    })
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;

    @NotBlank
    @Size(max = 20)
    private String customerName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    private String avatar;
    private String phone;
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customerRole",
        joinColumns = @JoinColumn(name = "customerID"),
        inverseJoinColumns = @JoinColumn(name = "croleID"))
    private Set<CRole> cRoles = new HashSet<>();

    public Customer(@NotBlank @Size(max = 20) String customerName, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password) {
        this.customerName = customerName;
        this.email = email;
        this.password = password;
    }

    public Customer() {
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<CRole> getcRoles() {
        return cRoles;
    }

    public void setcRoles(Set<CRole> cRoles) {
        this.cRoles = cRoles;
    }
}
