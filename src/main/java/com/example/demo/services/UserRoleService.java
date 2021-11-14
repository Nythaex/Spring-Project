package com.example.demo.services;

import com.example.demo.models.entities.UserRole;
import com.example.demo.models.enums.Role;

public interface UserRoleService {
    UserRole getRoleById(Long i);

    void addRole(Role admin);
}
