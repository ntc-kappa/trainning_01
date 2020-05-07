package com.tas.validator;

import com.tas.converter.UserConverter;
import com.tas.entity.UserEntity;
import com.tas.repository.UserRepository;
import com.tas.service.UserService;
import com.tas.validator.annotation.UsernameFormat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameFormatValidator implements ConstraintValidator<UsernameFormat,String> {

    @Autowired
    UserService userService;

    @Autowired
    UserConverter userConverter;

    @Override
    public void initialize(UsernameFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userService.findByUserName(value);
    }


}
