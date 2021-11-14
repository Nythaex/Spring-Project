package com.example.demo.services.impl;

import com.example.demo.models.entities.UserRole;
import com.example.demo.models.enums.Role;
import com.example.demo.repositories.UserRoleRepository;
import com.example.demo.services.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole getRoleById(Long i) {
        return userRoleRepository.getById(i);
    }

    @Override
    public void addRole(Role admin) {
        userRoleRepository.save(new UserRole(admin));
    }
}
