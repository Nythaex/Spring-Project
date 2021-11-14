package com.example.demo.utils.validations.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValidatorEmail.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail{
    String message() default "Contains in Database";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
