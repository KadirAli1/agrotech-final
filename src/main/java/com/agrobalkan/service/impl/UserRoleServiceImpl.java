package com.agrobalkan.service.impl;

import com.agrobalkan.model.UserRole;
import com.agrobalkan.repository.jpa.UserRoleRepository;
import com.agrobalkan.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository repo;

    @Override
    public List<UserRole> findAll() {
        return repo.findAll();
    }

    @Override
    public List<UserRole> findAllByOrderByIdDesc() { return repo.findAllByOrderByIdDesc(); }
}
