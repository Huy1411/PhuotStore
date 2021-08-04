package com.example.phuotstore.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cRole")
public class CRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cRoleID;

    @Column(length = 20)
    private String cRoleName;

    @ManyToMany(mappedBy = "cRoles")
    private Set<Customer> customers;

    public Long getcRoleID() {
        return cRoleID;
    }

    public void setcRoleID(Long cRoleID) {
        this.cRoleID = cRoleID;
    }

    public String getcRoleName() {
        return cRoleName;
    }

    public void setcRoleName(String cRoleName) {
        this.cRoleName = cRoleName;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
