package com.example.demo.models.services;

import com.example.demo.models.bindings.RegisterBinding;
import com.example.demo.models.enums.UserType;
import com.example.demo.utils.validations.user.UniqueEmail;
import com.example.demo.utils.validations.user.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterService {

    private String username;


    private String password;


    private String email;

    private String town;

    private UserType userType;

    public RegisterService(String username, String password, String email, String town, UserType userType) {
        this.username = username;
        this.password = password;

        this.email = email;
        this.town = town;
        this.userType = userType;
    }

    public RegisterService() {
    }

    public String getUsername() {
        return username;
    }

    public RegisterService setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterService setPassword(String password) {
        this.password = password;
        return this;
    }


    public String getEmail() {
        return email;
    }

    public RegisterService setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTown() {
        return town;
    }

    public RegisterService setTown(String town) {
        this.town = town;
        return this;
    }

    public UserType getUserType() {
        return userType;
    }

    public RegisterService setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }
}
