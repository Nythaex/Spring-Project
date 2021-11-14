package com.example.demo.utils.validations.user;




import com.example.demo.services.ShelterService;
import com.example.demo.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidatorEmail implements ConstraintValidator<UniqueEmail,String> {
    private final UserService userService;


    public UniqueValidatorEmail(UserService companyService) {
        this.userService = companyService;

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return  !userService.existByEmail(value);
    }
}
