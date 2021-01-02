package com.agrobalkan.service;

import com.agrobalkan.model.User;

public interface UserService extends BaseEntityService<User> {

    User findByUsername(String username);

    public Long count();
}
