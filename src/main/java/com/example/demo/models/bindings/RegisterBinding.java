package com.example.demo.models.bindings;

import com.example.demo.models.enums.UserType;
import com.example.demo.utils.validations.user.UniqueEmail;
import com.example.demo.utils.validations.user.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterBinding {

    @Size(min = 4,max = 20)
    @NotBlank
    @UniqueUsername
    private String username;

    @Size(min = 4,max = 20)
    @NotBlank
    private String password;

    @Size(min = 4,max = 20)
    @NotBlank
    private String confirmPassword;

    @NotBlank
    @UniqueEmail
    @Email
    private String email;

    @Size(min = 1)
    private String town;

    @NotNull
    private UserType userType;

    public RegisterBinding(String username, String password,String confirmPassword, String email, String town, UserType userType) {
        this.username = username;
        this.password = password;
        this.confirmPassword=confirmPassword;
        this.email = email;
        this.town = town;
        this.userType = userType;
    }

    public RegisterBinding() {
    }

    public String getUsername() {
        return username;
    }

    public RegisterBinding setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterBinding setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterBinding setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterBinding setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTown() {
        return town;
    }

    public RegisterBinding setTown(String town) {
        this.town = town;
        return this;
    }

    public UserType getUserType() {
        return userType;
    }

    public RegisterBinding setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }
}
