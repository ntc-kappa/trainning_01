package com.tas.validator.annotation;

import com.tas.validator.DateFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateFormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {
    String message() default "Điền ngày/tháng/năm (VD 01/01/2020) !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
