package com.example.demo.web;

import com.example.demo.models.entities.Town;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.TownRepository;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class AdminControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TownRepository townRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.save(new User().setUsername("test").setEmail("test@test").setPassword("1234").setTown(townRepository.getByName("Yambol")));
    }


    @AfterEach
    void delete() {
        userRepository.deleteAll();

    }


    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_GetAdmin() throws Exception {
        mockMvc
                .perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }



}