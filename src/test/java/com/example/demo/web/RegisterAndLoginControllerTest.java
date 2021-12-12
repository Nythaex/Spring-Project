package com.example.demo.web;

import com.example.demo.models.entities.User;
import com.example.demo.models.enums.UserType;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class RegisterAndLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init(){
        userRepository.deleteAll();
    }

    @AfterEach
    void delete() {
        userRepository.deleteAll();
    }

    @Test
    void test_PostRegister_CreatesNewUser() throws Exception {
        mockMvc
                .perform(post("/register")
                        .param("username", "Test")
                        .param("email", "test@test.bg")
                        .param("password", "12345")
                        .param("confirmPassword", "12345")
                        .param("town","Yambol")
                        .param("userType", UserType.USER.toString())
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        assertEquals(1, userRepository.count());

        User user = userRepository.findByUsername("Test");

        assertEquals("Test", user.getUsername());
        assertEquals("test@test.bg", user.getEmail());
    }

    @Test
    void test_PostRegister_CreatesNewUserFailed() throws Exception {
        mockMvc
                .perform(post("/register")
                        .param("username", "")
                        .param("email", "test@test.bg")
                        .param("password", "")
                        .param("confirmPassword", "12345")
                        .param("town","Yambol")
                        .param("userType", UserType.USER.toString())
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"));

        assertEquals(0, userRepository.count());

    }

    @Test
    void test_GetLoginForm_OpensLoginForm() throws Exception {
        mockMvc
                .perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
    @Test
    void test_GetRegisterForm_OpensRegisterForm() throws Exception {
        mockMvc
                .perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }
}