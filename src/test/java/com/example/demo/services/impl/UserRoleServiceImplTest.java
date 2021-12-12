package com.example.demo.services.impl;

import com.example.demo.models.entities.Town;
import com.example.demo.models.entities.UserRole;
import com.example.demo.models.enums.Role;
import com.example.demo.repositories.TownRepository;
import com.example.demo.repositories.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRoleServiceImplTest {

    @Mock
    private UserRoleRepository userRoleRepository;
    private UserRoleServiceImpl userRoleService;


    @BeforeEach
    void init() {


        userRoleService=new UserRoleServiceImpl(userRoleRepository);

    }

    @Test
    void test_save(){
        Mockito.when(userRoleRepository.save(Mockito.any(UserRole.class))).thenAnswer(i -> i.getArguments()[0]);


        userRoleService.addRole(Role.USER);
    }

}