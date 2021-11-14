package com.example.demo.models.bindings;

import com.example.demo.models.enums.UserType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginBinding {

    @Email
    @NotBlank
    private String email;

    @Size(min = 4,max = 20)
    @NotBlank
    private String password;

    public LoginBinding() {
    }

    public LoginBinding(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public LoginBinding setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginBinding setPassword(String password) {
        this.password = password;
        return this;
    }

}
