package com.agrobalkan.service;

import com.agrobalkan.model.UserRole;

import java.util.List;

public interface UserRoleService {

    public List<UserRole> findAll();

    public List<UserRole> findAllByOrderByIdDesc();
}
