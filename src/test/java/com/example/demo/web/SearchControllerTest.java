package com.example.demo.web;

import com.example.demo.models.entities.User;
import com.example.demo.repositories.TownRepository;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SearchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.save(new User().setUsername("test").setTown(townRepository.getByName("Yambol")).setEmail("test@asf").setPassword("12345"));
    }

    @Test
    @WithMockUser(username = "test")
    void test_GetSearch() throws Exception {
        mockMvc
                .perform(get("/search"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("towns"))
                .andExpect(view().name("search"));

    }
    @Test
    @WithMockUser(username = "test")
    void test_GetResult() throws Exception {
        mockMvc
                .perform(get("/search/result"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/search"));

    }

}