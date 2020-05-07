package com.tas.validator.annotation;

import com.tas.validator.UsernameFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameFormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameFormat {
    String message() default "UserName is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
