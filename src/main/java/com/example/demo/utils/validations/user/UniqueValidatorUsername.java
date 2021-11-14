package com.example.demo.utils.validations.user;




import com.example.demo.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidatorUsername implements ConstraintValidator<UniqueUsername,String> {
    private UserService userService;

    public UniqueValidatorUsername(UserService companyService) {
        this.userService = companyService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      return  !userService.existByUsername(value);
    }
}
