package com.example.phuotstore.repository;

import com.example.phuotstore.model.Product;
import com.example.phuotstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT p FROM Role p")
    List<Role> getRoles();

}
