package com.example.demojwt.service.imp;

import com.example.demojwt.entity.RoleEntity;
import com.example.demojwt.repository.RoleRepository;
import com.example.demojwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }
}
