package com.agrobalkan.repository.jpa;

import com.agrobalkan.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    public List<UserRole> findAllByOrderByIdDesc();
}
