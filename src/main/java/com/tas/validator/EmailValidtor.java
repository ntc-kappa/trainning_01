package com.tas.validator;

import com.tas.converter.UserConverter;
import com.tas.entity.UserEntity;
import com.tas.service.UserService;
import com.tas.validator.annotation.EmailValid;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidtor implements ConstraintValidator<EmailValid, String> {

    @Autowired
    UserService userService;
    @Autowired
    UserConverter userConverter;

    @Override
    public void initialize(EmailValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userService.findEmail(value);
    }


}
