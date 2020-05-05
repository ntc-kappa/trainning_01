package com.tas.validator;

import com.tas.validator.annotation.DateFormat;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {

    @Override
    public void initialize(DateFormat dateFormat) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null && s.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$")) {
            return true;
        }
        return false;
    }

}
