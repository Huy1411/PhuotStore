package com.example.phuotstore.service.impl;

import com.example.phuotstore.model.CRole;
import com.example.phuotstore.repository.CRoleRepository;
import com.example.phuotstore.service.CRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CRoleServiceImpl implements CRoleService {

    @Autowired
    private CRoleRepository cRoleRepository;

    @Override
    public List<CRole> getCRoles() {
        try{
            List<CRole> cRoles = cRoleRepository.getCRoles();
            return cRoles;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }




}
