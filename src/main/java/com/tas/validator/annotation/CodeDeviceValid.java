package com.tas.validator.annotation;

import com.tas.validator.DateFormatValidator;
import com.tas.validator.DeviceFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DeviceFormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeDeviceValid {

    String message() default "Mã mặt hàng đã tồn tại !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}