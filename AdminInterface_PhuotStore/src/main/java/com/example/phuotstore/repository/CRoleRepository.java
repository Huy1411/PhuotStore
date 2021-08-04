package com.example.phuotstore.repository;

import com.example.phuotstore.model.CRole;
import com.example.phuotstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CRoleRepository extends JpaRepository<CRole, Long> {
    @Query("SELECT r FROM CRole r WHERE r.cRoleName = ?1")
    Optional<CRole> findByCRoleName(String cRoleName);

    @Query("SELECT p FROM CRole p")
    List<CRole> getCRoles();

}
