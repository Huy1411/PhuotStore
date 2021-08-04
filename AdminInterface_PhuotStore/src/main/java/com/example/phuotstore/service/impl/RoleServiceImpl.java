package com.example.phuotstore.service.impl;

import com.example.phuotstore.model.Role;
import com.example.phuotstore.repository.RoleRepository;
import com.example.phuotstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        try{
            List<Role> roles = roleRepository.getRoles();
            return roles;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }




}
