package com.tas.validator;

import com.tas.entity.DeviceEntity;
import com.tas.repository.DeviceRepository;
import com.tas.validator.annotation.CodeDeviceValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class DeviceFormatValidator implements ConstraintValidator<CodeDeviceValid, String> {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public void initialize(CodeDeviceValid codeDeviceValid) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null) {
            DeviceEntity deviceEntity = deviceRepository.findOneByCode(s);
            if (deviceEntity != null) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
